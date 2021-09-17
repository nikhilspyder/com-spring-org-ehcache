package com.spring.orgehcache.eventlistener;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheEventLogger implements CacheEventListener<Object, Object> {
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheEventLogger.class);
 
    @Override
	public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
		LOGGER.info("phoneNumber - {}, oldValue - {}, NewValue - {}", cacheEvent.getKey(), cacheEvent.getOldValue(),
				cacheEvent.getNewValue());
	}
    
}