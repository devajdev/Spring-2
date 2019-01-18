package com.nt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value={PersistenceConfig.class,ServiceConfig.class,AopConfig.class})
public class AppConfig {

}
