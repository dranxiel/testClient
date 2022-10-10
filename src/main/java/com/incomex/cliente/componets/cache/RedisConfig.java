package com.incomex.cliente.componets.cache;


import com.incomex.cliente.componets.util.Settings;
import io.lettuce.core.ReadFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;


@Configuration
public class RedisConfig {
	
	@Autowired
	private Settings setting;
		
	@Bean
	LettuceConnectionFactory connectionFactory()
	{
			RedisStandaloneConfiguration configuracion = new RedisStandaloneConfiguration();
			configuracion.setHostName(setting.getRedisHost().split(":")[0]);
			configuracion.setPort(Integer.parseInt(setting.getRedisHost().split(":")[1]));
			configuracion.setPassword(setting.getRedisPassword());
									
		    return new LettuceConnectionFactory(configuracion, getClientConfiguration());

	}
		
	private LettuceClientConfiguration getClientConfiguration() {
		return LettuceClientConfiguration.builder()			
				.readFrom(ReadFrom.REPLICA_PREFERRED)				
				.commandTimeout(Duration.ofMillis(setting.getRedisTimeout()))
			    .shutdownTimeout(Duration.ZERO)
			    .build();		
	}
	
	@Bean
	StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}
		
	
}
