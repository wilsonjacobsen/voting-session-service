package com.sicredi.votingsessionservice.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="om.sicredi.votingsessionservice.business")
public class AppConfig{
}