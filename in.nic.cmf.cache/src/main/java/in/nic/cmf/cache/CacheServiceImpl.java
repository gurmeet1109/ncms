package in.nic.cmf.cache;

import in.nic.cmf.contract.CMFService;
import in.nic.cmf.contract.ServiceName;
import in.nic.cmf.convertors.ConvertorUtils;
import in.nic.cmf.convertors.FormatJson;
import in.nic.cmf.convertors.FormatXml;
import in.nic.cmf.exceptions.GenericException;
import in.nic.cmf.logger.LogTracer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;

@ServiceName ("cache")
public class CacheServiceImpl implements CMFService {

    public LogTracer  log = null;

    public RedisCache rc;

    private FormatXml formatxml;

    private void assignDomain(String domain) {
        rc = RedisCache.getInstanceof(domain);
        formatxml = FormatXml.getInstanceof(domain);
    }

    private String getId(HashMap o) {
        String Id = null;
        Id = (String) (o.containsKey("id") ? o.get("id")
                : o.containsKey("Id") ? o.get("Id") : null);
        return Id;
    }

    private Map getFieldNameMapping(String domain) {
        try {
            String filePath = System.getProperty("user.dir")
                    + ".cache/src/main/resources/FieldNameMapping.json";
            InputStream is = new FileInputStream(filePath);
            Map outputMap = FormatJson.getInstanceof(domain).in(
                    IOUtils.toString(is));
            outputMap.put("fieldNameMapping",
                    (HashMap) outputMap.get("dmsfieldNameMapping"));
            return outputMap;
        } catch (GenericException e) {
            log.error(e.getMessage());
            throw e;
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error("Exception :::::: " + e);
            throw new GenericException(domain, "ERR-CACHE-0016");
        }
    }

    private String getEntityName(HashMap<String, Object> o) {
        String entityName = null;
        if (o.containsKey("EntityType")) {
            entityName = (String) o.get("EntityType");
        }
        return entityName;
    }

    private String getXMLContent(String domain, HashMap xmlMap) {
        return (String) formatxml.out(xmlMap,
                (HashMap<String, Object>) getFieldNameMapping(domain));
    }

    private Map<String, Object> processXmlPost(String domain, String collections) {
        Map result = FormatXml.getInstanceof(domain).in(collections);
        Map<String, Object> xmlContent = (Map) result.get("Collections");
        if (xmlContent == null) {
            throw new GenericException(domain, "ERR-CACHE-0001",
                    "XML should have Collections tag as parent");
        }
        String xml = "";
        for (Entry<String, Object> e : xmlContent.entrySet()) {
            if (ConvertorUtils.getInstanceof(domain).isHashMap(e.getValue())) {
                HashMap contentXml = new HashMap();
                contentXml.put(e.getKey(), e.getValue());
                String Id = getId((HashMap) e.getValue());
                if (null != Id) {
                    xml = getXMLContent(domain, contentXml);
                    rc.set(Id, xml);
                    System.out.println("xml ID: " + Id + ":::::xml::::" + xml);
                } else continue;

            } else if (ConvertorUtils.getInstanceof(domain).isArrayList(
                    e.getValue())) {
                processArrayList(domain, (ArrayList<HashMap>) e.getValue());
            }
        }
        return xmlContent;
    }

    private void processArrayList(String domain, ArrayList<HashMap> xmlContent) {
        String xml = "";
        String Id = null;
        for (HashMap e : xmlContent) {
            if (e.containsKey("EntityType")) {
                Id = getId(e);
                if (null != Id) {
                    HashMap listcontent = new HashMap();
                    String entitytype = getEntityName(e);
                    listcontent.put(entitytype, e);
                    xml = getXMLContent(domain, listcontent);
                    rc.set(Id, xml);
                    System.out.println("processArrayList xml ID: " + Id
                            + ":::::xml::::" + xml);
                } else continue;
            }
        }
    }

    public Map<String, Map<String, Object>> add(String domain, String entity,
            Map<String, Map<String, Object>> parameters) {
        assignDomain(domain);
        Map<String, Object> input = parameters.get("input");
        String collections = (String) input.get("content");
        Map<String, Object> resultMap = processXmlPost(domain, collections);
        String result = (String) FormatXml.getInstanceof(domain).out(
                (HashMap<String, Object>) resultMap);
        return buildResponseMap(parameters, result);
    }

    public Map<String, Map<String, Object>> deleteByID(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> parameters) {
        assignDomain(domain);
        try {
            rc.delete(id);
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CACHE-0000", this
                    .getClass().getSimpleName() + ":deleteByID()", e);
        }
        return buildResponseMap(parameters, id);
    }

    public Map<String, Map<String, Object>> deleteByQuery(String domain,
            String entity, Map<String, Map<String, Object>> queryParams) {
        throw new GenericException(domain, "ERR-CACHE-0003");
    }

    public Map<String, Map<String, Object>> find(String domain, String entity,
            Map<String, Map<String, Object>> queryParams) {
        return null;
    }

    public Map<String, Map<String, Object>> findById(String domain,
            String entity, String id,
            Map<String, Map<String, Object>> parameters) {
        assignDomain(domain);
        String result = "";
        try {
            result = rc.get(id);
        } catch (Exception e) {
            throw new GenericException(domain, "ERR-CACHE-0002");
        }
        return buildResponseMap(parameters, result);
    }

    public void setLogTracer(LogTracer log) {
        this.log = log;
    }

    @Override
    public Map<String, Map<String, Object>> addManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    @Override
    public Map<String, Map<String, Object>> findManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    @Override
    public Map<String, Map<String, Object>> deleteManage(final String domain,
            final String service, final String entity,
            final Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-CONFIG-0001");
    }

    private Map<String, Map<String, Object>> buildResponseMap(
            Map<String, Map<String, Object>> arg0, String content) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("statusCode", "200");
        response.put("content", content);
        arg0.put("output", response);
        return arg0;
    }

    public Map<String, Map<String, Object>> read(String domain, String entity,
            Map<String, Map<String, Object>> parameters) {
        throw new GenericException(domain, "ERR-0000");
    }
}
