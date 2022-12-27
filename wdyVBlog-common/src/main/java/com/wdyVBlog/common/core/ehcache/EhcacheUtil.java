package com.wdyVBlog.common.core.ehcache;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Results;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @Description
 * @author wdy
 * @Date 2022-11-7
 */
@Component
@Slf4j
public class EhcacheUtil {

    /**
     * [单例的Ehcache缓存工具类]
     */
    private static final String DEFAULT_CACHE_NAME = "department";
    private volatile static CacheManager manager;

    /**
     * [获取缓存管理类实例,双重锁确保缓存管理类单例]
     */
    @SneakyThrows
    private static CacheManager getCacheManagerInstance() {
        if (manager == null) {
            synchronized (EhcacheUtil.class) {
                if (manager == null) {
//                    url = EhcacheUtil.class.getResource(PATH);
                    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
                    Resource resource = resolver.getResource("cache/ehcache.xml");
                    manager = CacheManager.create(resource.getInputStream());
                }
            }
        }
        return manager;
    }

    /**
     * [通过缓存名{cacheName}获取缓存对象]
     */
    private static Cache getCache(String cacheName) {
        Cache cache = getCacheManagerInstance().getCache(cacheName);
        return cache;
    }

    /**
     * [将{key:value}存到默认缓存[DefaultCache]中]
     */
    public static void setCacheObject(String key, Object value) {
        put(DEFAULT_CACHE_NAME, key, value);
    }

    /**
     * [将{key:value}存到默认缓存[DefaultCache]中,存活时间和钝化时间都为{timeToLiveSeconds}秒]
     */
    public static void setCacheObject(String key, Object value, int timeToLiveSeconds) {
        put(DEFAULT_CACHE_NAME, key, value, timeToLiveSeconds);
    }

    /**
     * [将{key:value}存到默认缓存[DefaultCache]中,存活时间{timeToLiveSeconds}秒,钝化时间{	 * timeToIdleSeconds}秒]
     */
    public static void setCacheObject(String key, Object value, int timeToLiveSeconds, int timeToIdleSeconds) {
        put(DEFAULT_CACHE_NAME, key, value, timeToLiveSeconds, timeToIdleSeconds);
    }

    /**
     * [将{key:value}存到缓存{cacheName}中]
     */
    public static void put(String cacheName, String key, Object value) {
        synchronized (key.intern()) {
            Cache cache = getCache(cacheName);
            Element element = new Element(key, value);
            cache.put(element);
        }
    }

    /**
     * [将{key:value}存到缓存{cacheName}中,存活时间和钝化时间都为{timeToLiveSeconds}秒]
     */
    public static void put(String cacheName, String key, Object value, int timeToLiveSeconds) {
        put(cacheName, key, value, timeToLiveSeconds, timeToLiveSeconds);
    }

    /**
     * [将{key:value}存到缓存{cacheName}中,存活时间{timeToLiveSeconds}秒,钝化时间{	 * timeToIdleSeconds}秒]
     */
    public static void put(String cacheName, String key, Object value, int timeToLiveSeconds, int timeToIdleSeconds) {
        synchronized (key.intern()) {
            Cache cache = getCache(cacheName);
            Element element = new Element(key, value);
            element.setEternal(false);
            element.setTimeToLive(timeToLiveSeconds);
            element.setTimeToIdle(timeToIdleSeconds);
            cache.put(element);
        }
    }

    /**
     * [从默认缓存[DefaultCache]中获取{key}对应的值]
     */
    public static Object getCacheObject(String key) {
        return get(DEFAULT_CACHE_NAME, key);
    }

    /**
     * [从缓存{cacheName}中获取{key}对应的值]
     */
    public static Object get(String cacheName, String key) {
        synchronized (key.intern()) {
            Cache cache = getCache(cacheName);
            Element element = cache.get(key);
            return element == null ? null : element.getObjectValue();
        }
    }

    /**
     * [从默认缓存[DefaultCache]中移除{key}的缓存记录]
     */
    public static void deleteObject(String key) {
        remove(DEFAULT_CACHE_NAME, key);
    }


    public static void deleteObject(Collection key) {
        getCache(DEFAULT_CACHE_NAME).removeAll(key);
    }

    /**
     * [从缓存{cacheName}中移除{key}的缓存记录]
     */
    public static synchronized void remove(String cacheName, String key) {
        getCache(cacheName).remove(key);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public static Collection<String> keys(final String pattern)
    {
        Attribute<String> department = getCache(DEFAULT_CACHE_NAME).getSearchAttribute("key");
        Results execute = getCache(DEFAULT_CACHE_NAME).createQuery().addCriteria(department.ilike(pattern)).includeAttribute(department).includeKeys().execute();
        return execute.all().stream().map(e->String.valueOf(e.getKey())).collect(Collectors.toCollection(TreeSet::new));
    }

    public static boolean hasKey(String cacheKey) {
        Cache cache = getCache(DEFAULT_CACHE_NAME);
        Element element = cache.get(cacheKey);
        return element != null;
    }
}



