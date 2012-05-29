package in.nic.cmf.properties.helper;

import in.nic.cmf.logger.LogTracer;
import in.nic.cmf.properties.contract.StorageProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqliteStorage implements StorageProvider {

    private LogTracer  log              = null;
    private Connection connection       = null;
    private String     connectionString = "jdbc:sqlite:/opt/cmf/cmfconfiguration.db";

    public static StorageProvider getInstanceOf(String domain) {
        return new SqliteStorage(domain);
    }

    private SqliteStorage(String domain) {
        intiallize();
    }

    public void setLogger(LogTracer log) {
        this.log = log;
    }

    private void intiallize() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        connection.close();
    }

    private void createTable(String table) {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager
                    .getConnection("jdbc:sqlite:/opt/cmf/cmfconfiguration.db");
            Statement stat = connection.createStatement();
            String createQry = "CREATE TABLE IF NOT EXISTS "
                    + table
                    + "(domain text not null,entity text not null,configkey text not null,"
                    + "configvalue text not null,"
                    + "PRIMARY KEY(domain,entity,configkey));";
            if (stat.execute(createQry)) {
                log.debug("create query: " + createQry);
            }
            stat.close();
        } catch (ClassNotFoundException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String get(String domain, String service, String entity, String key) {

        domain = domain.toLowerCase();
        service = service.toLowerCase();
        entity = entity.toLowerCase();
        key = key.toLowerCase();

        String configval = null;
        // String SelectQuery = "select configvalue from " + service + " where"
        // + " domain= '" + domain + "' and entity= '" + entity
        // + "and configkey='" + key + "';";
        // Statement st;
        String SelectQuery = "select configvalue from " + service
                + " where domain= ? and entity= ? and configkey=?;";

        log.debug("selectquery1: " + SelectQuery);
        ResultSet rs;
        PreparedStatement ps;

        String inputparam = "domain:" + domain + "  entity: " + entity
                + "  key: " + key;
        log.debug("inputParams: " + inputparam);
        try {
            // st = connection.createStatement();
            // rs = st.executeQuery(SelectQuery);
            ps = connection.prepareStatement(SelectQuery);
            ps.setString(1, domain);
            ps.setString(2, entity);
            ps.setString(3, key);

            rs = ps.executeQuery();
            while (rs.next()) {
                configval = rs.getString("configvalue");
            }
            rs.close();
            ps.close();
            // st.close();
        } catch (SQLException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }
        log.debug("output:" + configval);
        return configval;
    }

    @Override
    public Map<String, String> get(String domain, String service,
            String entity, List<String> keys) {

        domain = domain.toLowerCase();
        service = service.toLowerCase();
        entity = entity.toLowerCase();

        Map<String, String> prop = new HashMap<String, String>();
        String configkeys = implodeArray(keys, ",").toLowerCase();
        // String SelectQuery = "select * from " + service + " where"
        // + " domain= '" + domain + "' and entity= '" + entity
        // + "and configkey=IN(" + keys
        // + ") order by domain,entity,configkey;";

        String SelectQuery = "select * from " + service
                + " where domain= ? and entity= ? and configkey=IN(?) "
                + "order by domain,entity,configkey;";

        log.debug("selectquery2: " + SelectQuery);
        String inputparam = "domain:" + domain + "  entity: " + entity
                + "  key: " + configkeys;
        log.debug("inputParams: " + inputparam);
        ResultSet rs;
        // Statement st;
        PreparedStatement ps;
        try {
            // st = connection.createStatement();
            // rs = st.executeQuery(SelectQuery);
            ps = connection.prepareStatement(SelectQuery);
            ps.setString(1, domain);
            ps.setString(2, entity);
            ps.setString(3, configkeys);
            rs = ps.executeQuery();
            while (rs.next()) {
                prop.put(rs.getString("configkey"), rs.getString("configvalue"));
            }
            rs.close();
            // st.close();
            ps.close();
        } catch (SQLException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }
        log.debug("output:" + prop);
        return prop;
    }

    @Override
    public Map<String, String> get(String domain, String service, String entity) {
        Map<String, String> prop = new HashMap<String, String>();

        domain = domain.toLowerCase();
        service = service.toLowerCase();
        entity = entity.toLowerCase();
        String SelectQuery = "select configkey,configvalue from "
                + service
                + " where domain= ? and entity= ? order by domain,entity,configkey;";
        // String SelectQuery = "select * from " + service + " where"
        // + " domain= '" + domain + "' and entity= '" + entity
        // + "' order by domain,entity,configkey;";

        log.debug("selectquery2: " + SelectQuery);
        String inputparam = "domain:" + domain + "  entity: " + entity;
        log.debug("inputParams: " + inputparam);
        ResultSet rs;
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(SelectQuery);
            ps.setString(1, domain);
            ps.setString(2, entity);
            rs = ps.executeQuery();

            while (rs.next()) {
                prop.put(rs.getString("configkey"), rs.getString("configvalue"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }
        log.debug("output:" + prop);
        return prop;
    }

    @Override
    public Map<String, Map<String, String>> get(String domain, String service) {
        Map<String, Map<String, String>> entityprop = new HashMap<String, Map<String, String>>();

        domain = domain.toLowerCase();
        service = service.toLowerCase();
        String SelectQuery = "select entity,configkey,configvalue from "
                + service + " where domain= ? "
                + "order by domain,entity,configkey;";
        log.debug("selectquery2: " + SelectQuery);
        String inputparam = "domain:" + domain;
        log.debug("inputParams: " + inputparam);
        ResultSet rs;
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(SelectQuery);
            ps.setString(1, domain);
            rs = ps.executeQuery();
            entityprop = new HashMap<String, Map<String, String>>();
            while (rs.next()) {

                String entity = rs.getString("entity");
                String key = rs.getString("configkey");
                String val = rs.getString("configvalue");

                Map<String, String> prop = null;
                if (entityprop.containsKey(entity)) {
                    prop = entityprop.get(entity);
                } else {
                    prop = new HashMap<String, String>();
                }
                prop.put(key, val);
                entityprop.put(entity, prop);
            }
            rs.close();
        } catch (SQLException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }
        log.debug("output:" + entityprop);
        return entityprop;
    }

    @Override
    public Integer put(String domain, String service, String entity,
            String key, String value) {

        Integer output = null;
        try {
            createTable(service);
            String prepSt = "INSERT OR REPLACE INTO " + service
                    + " values (?,?,?,?);";
            log.debug("INSERT/REPLACE QUERY1: " + prepSt);
            PreparedStatement prep = connection.prepareStatement(prepSt);
            prep.setString(1, domain.toLowerCase());
            prep.setString(2, entity.toLowerCase());
            prep.setString(3, key.toLowerCase());
            prep.setString(4, value);
            output = prep.executeUpdate();
        } catch (SQLException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }
        String inputparam = "domain:" + domain + "  entity: " + entity
                + "  key: " + key + " value:" + value;
        log.debug("inputParams: " + inputparam);
        log.debug("output:" + output);
        return output;
    }

    @Override
    public Integer put(String domain, String service, String entity,
            Map<String, String> keys) {

        Integer output = null;
        try {
            createTable(service);
            String prepSt = "INSERT OR REPLACE INTO " + service
                    + " values (?,?,?,?);";
            log.debug("INSERT/REPLACE QUERY2: " + prepSt);
            String inputparam = "domain:" + domain + "  entity: " + entity;

            PreparedStatement prep = connection.prepareStatement(prepSt);
            for (String key : keys.keySet()) {
                prep.setString(1, domain.toLowerCase());
                prep.setString(2, entity.toLowerCase());
                prep.setString(3, key.toLowerCase());
                prep.setString(4, keys.get(key));
                prep.addBatch();
                inputparam += "  key: " + key + " value:" + keys.get(key);
            }
            connection.setAutoCommit(false);
            output = prep.executeBatch().length;
            connection.setAutoCommit(true);
            log.debug("inputParams: " + inputparam);
        } catch (SQLException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }

        log.debug("output:" + output);
        return output;
    }

    @Override
    public Integer put(String domain, String service,
            Map<String, Map<String, String>> collection) {
        Integer output = null;
        try {
            createTable(service);
            String prepSt = "INSERT OR REPLACE INTO " + service
                    + " values (?,?,?,?);";
            log.debug("INSERT/REPLACE QUERY3: " + prepSt);
            String inputparam = "domain:" + domain;
            PreparedStatement prep = connection.prepareStatement(prepSt);
            for (String entity : collection.keySet()) {
                Map<String, String> config = (HashMap<String, String>) collection
                        .get(entity);
                for (String key : config.keySet()) {
                    prep.setString(1, domain.toLowerCase());
                    prep.setString(2, entity.toLowerCase());
                    prep.setString(3, key.toLowerCase());
                    prep.setString(4, config.get(key));
                    prep.addBatch();
                    inputparam += "  entity: " + entity + "  key: "
                            + key.toLowerCase() + " value:" + config.get(key);
                }
            }
            log.debug("inputparams: " + inputparam);
            connection.setAutoCommit(false);
            output = prep.executeBatch().length;
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }
        log.debug("output:" + output);
        return output;
    }

    @Override
    public Integer delete(String domain, String service, String entity,
            String key) {

        domain = domain.toLowerCase();
        service = service.toLowerCase();
        entity = entity.toLowerCase();
        key = key.toLowerCase();

        Integer output = null;
        String DeleteQuery = "delete from" + service + " where domain = ? "
                + "and entity = ? and configkey = ?;";
        log.debug("deletequery1: " + DeleteQuery);
        String inputparam = "domain:" + domain + "  entity: " + entity
                + "  key: " + key;
        log.debug("inputParams: " + inputparam);
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(DeleteQuery);
            ps.setString(1, domain);
            ps.setString(2, entity);
            ps.setString(3, key);
            output = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }
        log.debug("output:" + output);
        return output;
    }

    @Override
    public Map<String, Integer> delete(String domain, String service,
            String entity, List<String> keys) {

        domain = domain.toLowerCase();
        service = service.toLowerCase();
        entity = entity.toLowerCase();

        Map<String, Integer> outputMap = new HashMap<String, Integer>();
        String DeleteQuery = "delete from " + service + " where domain = ? "
                + "and entity = ? and configkey = ?;";
        log.debug("deletequery3: " + DeleteQuery);
        String inputparam = "domain:" + domain + "  entity: " + entity;
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(DeleteQuery);
            for (String key : keys) {
                ps.setString(1, domain);
                ps.setString(2, entity);
                ps.setString(3, key.toLowerCase());
                outputMap.put(key, ps.executeUpdate());
                inputparam += "  key: " + key;
            }
            ps.close();
        } catch (SQLException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }
        log.debug("inputParams: " + inputparam);
        log.debug("output:" + outputMap);
        return outputMap;
    }

    @Override
    public Integer delete(String domain, String service, String entity) {

        domain = domain.toLowerCase();
        service = service.toLowerCase();
        entity = entity.toLowerCase();

        Integer output = null;
        String DeleteQuery = "delete from " + service + " where domain = ? "
                + "and entity = ?;";
        log.debug("deletequery4: " + DeleteQuery);
        String inputparam = "domain:" + domain + "  entity: " + entity;
        log.debug("inputParams: " + inputparam);
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(DeleteQuery);
            ps.setString(1, domain);
            ps.setString(2, entity);
            output = ps.executeUpdate();;
            ps.close();
        } catch (SQLException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }
        // System.out.println("o" + output);
        log.debug("output:" + output);
        return output;
    }

    @Override
    public Integer delete(String domain, String service) {
        domain = domain.toLowerCase();
        service = service.toLowerCase();

        Integer output = null;
        String DeleteQuery = "delete from ? where domain = ?;";
        String inputparam = "domain:" + domain;
        log.debug("inputParams: " + inputparam);
        log.debug("deletequery5: " + DeleteQuery);
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(DeleteQuery);
            ps.setString(1, service);
            ps.setString(2, domain);
            output = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            log.debug(e.getMessage());
            e.printStackTrace();
        }
        log.debug("output:" + output);
        return output;
    }

    private String implodeArray(List<String> inputArray, String glueString) {

        String output = "";
        if (inputArray.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(inputArray.indexOf(0));
            for (int i = 1; i < inputArray.size(); i++) {
                sb.append(glueString);
                sb.append(inputArray.get(i));
            }
            output = sb.toString();
        }
        return output;
    }

    @Override
    public void setLogTracer(LogTracer log) {
        this.log = log;
    }

}
