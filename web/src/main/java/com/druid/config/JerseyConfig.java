package com.druid.config;

import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by druid on 21/02/17.
 */
@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("com.druid.mvc");
        register(MultiPart.class);
    }
}
