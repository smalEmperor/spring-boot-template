package com.template.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * spring boot redis配置(lettuce客户端)
 * @author df
 * @date 2019/8/6
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        ObjectMapper mapper = new ObjectMapper();
        template.setConnectionFactory(connectionFactory);
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        serializer.setObjectMapper(mapper);
        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() {
        //创建配置
        Config config = new Config();
        /**
         *如果有密码：
         *config.useSingleServer().setAddress("redis://120.78.179.242:6379").setPassword("123456")
         **/
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(1);
        //根据Config创建出RedissonClient示例
        return Redisson.create(config);
    }

    /**
     * 对hash类型的数据操作
     * @param redisTemplate
     */
    @Bean(value = "hashRedis")
    public HashOperations<String,String,Object> hashOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForHash();
    }


    /**
     * 对redis字符串类型数据操作
     * @param redisTemplate
     */
    @Bean(value = "stringRedis")
    public ValueOperations<String,Object> valueOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForValue();
    }


    /**
     * 对链表类型的数据操作
     * @param redisTemplate
     */
    @Bean(value = "listRedis")
    public ListOperations<String,Object> listOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForList();
    }


    /**
     * 对无序类型集合类型数据操作
     * @param redisTemplate
     */
    @Bean(value = "SetRedis")
    public SetOperations<String,Object> setOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForSet();
    }

    /**
     * 对有序集合类型数据操作
     * @param redisTemplate
     */
    @Bean(value = "zsetRedis")
    public ZSetOperations<String,Object> zSetOperations(RedisTemplate<String,Object> redisTemplate){
        return redisTemplate.opsForZSet();
    }

}
