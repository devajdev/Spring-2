package com.nt.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.nt.dao.EmpDetailsRepository;

@Configuration
@ComponentScan(basePackages={"com.nt.service"})
@EnableJpaRepositories(basePackages={"com.nt.dao"})
@EntityScan(basePackages="com.nt.dao")
public class AppConfig {

}
