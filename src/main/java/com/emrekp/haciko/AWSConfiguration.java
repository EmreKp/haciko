package com.emrekp.haciko;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.data.redis.config.ConfigureRedisAction;

@Configuration
@Profile("aws")
public class AWSConfiguration {

  @Bean
  public static ConfigureRedisAction configureRedisAction() {
    return ConfigureRedisAction.NO_OP;
  }
}
