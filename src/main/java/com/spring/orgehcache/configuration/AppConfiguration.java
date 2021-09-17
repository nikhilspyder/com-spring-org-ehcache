package com.spring.orgehcache.configuration;

import java.time.Duration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.event.EventType;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.spring.orgehcache.domain.Student;
import com.spring.orgehcache.eventlistener.CacheEventLogger;

@Configuration
@EnableCaching
@ComponentScan(basePackages = "com.spring.netehcache")
public class AppConfiguration extends CachingConfigurerSupport {
	
	@Value("${custom.ehcache.timeToLiveSeconds}")
	private int timeToLiveSeconds;
	
	@Value("${custom.ehcache.maxEntriesLocalHeap}")
	private int maxEntriesLocalHeap;
	
	
	@Bean
	public CacheManager ehCacheManager() {
		CachingProvider cacheProvider = Caching.getCachingProvider();
		CacheManager cacheManager = cacheProvider.getCacheManager();

		CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
				.newEventListenerConfiguration(new CacheEventLogger(), EventType.CREATED, EventType.EXPIRED).unordered()
				.asynchronous();

		CacheConfigurationBuilder<String, Student> configuration = CacheConfigurationBuilder
				.newCacheConfigurationBuilder(String.class, Student.class,
						ResourcePoolsBuilder.newResourcePoolsBuilder().offheap(maxEntriesLocalHeap, MemoryUnit.MB))
				.add(cacheEventListenerConfiguration)
				.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(timeToLiveSeconds)));

		javax.cache.configuration.Configuration<String, Student> cacheConfig = Eh107Configuration
				.fromEhcacheCacheConfiguration(configuration);

		cacheManager.createCache("cachingStudent", cacheConfig);
		return cacheManager;

	}
	
	
}
