package com.druid.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by druid on 27/11/16.
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext ctx;
    public static ApplicationContext getContext(){
        return ctx;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
