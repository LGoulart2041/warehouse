package edu.dio.warehouse.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class WarehouseClientConfig {

    @Bean
    RestClient warehouseClient(@Value("${storefront.base-path}") final String basePath){
        return RestClient.builder().baseUrl(basePath).build();
    }
}
