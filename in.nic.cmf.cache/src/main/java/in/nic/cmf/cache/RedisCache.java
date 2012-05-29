package in.nic.cmf.cache;

import in.nic.cmf.properties.PropertiesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.pool.impl.GenericObjectPool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisCache implements Cachable {

    private static PropertiesUtil              putil;

    // private static final RedisCache rc = new RedisCache();

    private JedisPool                          pool           = null;
    private static HashMap<String, RedisCache> hashRedisCache = new HashMap<String, RedisCache>();
    private String domain;
    private RedisCache(String domain) {
    	this.domain= domain;
        putil = PropertiesUtil.getInstanceof(domain, "cache");

        GenericObjectPool.Config poolConfig = new GenericObjectPool.Config();

        String redisserver = putil.getProperty("redisHost");
        
        int redisport = new Integer(putil.getProperty("redisPort"));

        poolConfig.testOnBorrow = true;

        poolConfig.maxActive = Integer.parseInt(putil
                .getProperty("redisMaxConn"));

        poolConfig.maxWait = Long.parseLong(putil.getProperty("redisMaxWait"));

        poolConfig.maxIdle = Integer
                .parseInt(putil.getProperty("redisMaxIdle"));

        pool = new JedisPool(poolConfig, redisserver, redisport);
    }

    // public static RedisCache getInstance() {
    // return rc;
    // }

    public static RedisCache getInstanceof(String domain) {
        if (!hashRedisCache.containsKey(domain)) {
            hashRedisCache.put(domain, new RedisCache(domain));
        }
        return hashRedisCache.get(domain);
    }

    public synchronized void set(String key, String value) {
        Jedis j = getJedis();
        j.set(key, value);
        pool.returnResource(j);
    }

    public synchronized String get(String key) {
        String s = null;
        try {
            Jedis j = getJedis();
            s = j.get(key);
            pool.returnResource(j);
            return s;
        } catch (Exception e) {
            // do nothing but to add logging.
        }
        return s;
    }

    public synchronized void delete(String key) {
        Jedis j = getJedis();
        j.del(key);
        pool.returnResource(j);
    }

    public void set(String key, Object value) {
        set(key, (String) value);
    }

    public void set(String key, Object value, int ttl) {
        set(key, value);
    }

    public void set(HashMap<String, Object> multiKey) {
        Iterator<String> i = multiKey.keySet().iterator();
        while (i.hasNext()) {
            String key = i.next().toString();
            set(key, multiKey.get(key));
        }
    }

    public HashMap<String, Object> get(ArrayList<String> multiKey) {
        HashMap<String, Object> h = new HashMap<String, Object>();
        for (String key : multiKey) {
            h.put(key, get(key));
        }
        return h;
    }

    public void delete(ArrayList<String> multiKey) {
        for (String key : multiKey) {
            delete(key);
        }
    }

    private Jedis getJedis() {
        Jedis j = null;
        try {
            j = pool.getResource();
            if (!j.isConnected()) {
                j.connect();
            }
        } catch (Exception e) {
            // do nothing but add logging.
        }
        return j;
    }
}
