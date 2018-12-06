package com.casit.config.Redis;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.casit.config.Redis.FastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig{

	@Bean
	@ConditionalOnMissingBean(name = "redisTemplate")
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);

		// 使用Jackson2JsonRedisSerialize 替换默认序列化
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

		// 设置value的序列化规则和 key的序列化规则
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}


	@Bean
	@ConditionalOnMissingBean(StringRedisTemplate.class)
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}
	

	@Bean
	CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		// 初始化一个RedisCacheWriter
		RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
		// 设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现
		// ClassLoader loader = this.getClass().getClassLoader();
		// JdkSerializationRedisSerializer jdkSerializer = new
		// JdkSerializationRedisSerializer(loader);
		// RedisSerializationContext.SerializationPair<Object> pair =
		// RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);
		// RedisCacheConfiguration
		// defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
		RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
		// 设置默认超过期时间是3600秒
		defaultCacheConfig = defaultCacheConfig.entryTtl(Duration.ofSeconds(3600));
		// 初始化RedisCacheManager
		RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
		return cacheManager;
	}

}
