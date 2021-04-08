package com.amitdr.redis.sdk.data.Impl.configuration;

import com.amitdr.redis.sdk.data.Impl.repositories.DataRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public DataRepository dataRepository(AppProperties appProperties,
                                         @Qualifier("external-repository") DataRepository externalDataRepository,
                                         @Qualifier("in-memory-repository") DataRepository inMemoryDataRepository) {
        if (appProperties.getStorageEngine().equals("in-memory")) return inMemoryDataRepository;
        else if (appProperties.getStorageEngine().equals("external")) return externalDataRepository;
        return null;
    }
}
