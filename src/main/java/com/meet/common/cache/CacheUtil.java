/**   
 * @Title: CacheKit.java 
 * @Package com.car.commom.cache 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author baizhixing   
 * @date 2017年5月8日 下午3:28:51 
 * @version V1.0   
 */
package com.meet.common.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Title: CacheKit.java
 * @Package com.car.commom.cache
 * @Description: TODO(缓存工具类)
 * @author baizhixing
 * @date 2017年5月8日 下午3:28:51
 * @version V1.0
 */
@Component
public class CacheUtil {

	@Autowired
	CacheManager cacheManager;

	private Cache getOrAddCache(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			synchronized (cacheManager) {
				cache = cacheManager.getCache(cacheName);
				if (cache == null) {
					cacheManager.addCacheIfAbsent(cacheName);
					cache = cacheManager.getCache(cacheName);
				}
			}
		}
		return cache;
	}

	public void put(String cacheName, Object key, Object value) {
		getOrAddCache(cacheName).put(new Element(key, value));
	}
	public void remove(String cacheName, Object key){
		getOrAddCache(cacheName).remove(key);
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String cacheName, Object key) {
		Element element = getOrAddCache(cacheName).get(key);
		return element != null ? (T) element.getObjectValue() : null;
	}
}
