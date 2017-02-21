package com.druid.core.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by druid on 20/02/17.
 */
@ConfigurationProperties(locations = "classpath:config/messages")
public class Messages {
    public static final String SYSTEM_ERROR="系统错误！";
}
