package com.amitdr.redis.sdk.data.Impl.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("app-config")
public class AppProperties {

    private String storageEngine;
}
