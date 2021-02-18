package com.github.atomic.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class EnvironmentConfig implements EnvironmentAware {

    private Environment environment;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getAppName(){
        return this.environment.getProperty("spring.application.name");
    }

    public String getContextPath(){
        return this.environment.getProperty("server.context-path");
    }

    public String getPort(){
        return this.environment.getProperty("server.port");
    }

    public String getBaseUrl(){
        String contextPath = !StringUtils.isEmpty(this.getContextPath())? this.getContextPath(): "";
        return this.environment.getProperty("app.base-url")+contextPath+"/";
    }

    public String getFileDir(){
        return this.environment.getProperty("app.file-dir");
    }

    public String getTempFileDir(){
        return this.environment.getProperty("app.temp-file-dir");
    }



}
