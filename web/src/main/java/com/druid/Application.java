package com.druid;

import com.druid.config.JerseyConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by druid on 23/11/16.
 */
@ComponentScan(basePackages = {"com.druid.mvc","com.druid.config"})
@ImportResource({"classpath:dubbo/dubbo.xml"})
@SpringBootApplication
@EnableAutoConfiguration
@ServletComponentScan
public class Application {
    public static void main(String[] args) {
        //ConfigurableApplicationContext ctx = SpringApplication.run(Application.class);
        SpringApplication app = new SpringApplication(Application.class);
        app.run();
    }
}
