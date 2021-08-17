package com.jnk1124.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){

        /* 기본형태
           builder.routes()
                  .rout(r -> r.path()    //1 : patten이  들어오면
                              .filter()  // 3 필터등록
                              .uri())    // 2 : uri 로 라우팅
                  .build();
         */

        return builder.routes()
                .route(r -> r.path("/first-service/**")     //1 : patten이  들어오면
                             .filters(f -> f.addRequestHeader("first-request", "first-request-header")
                                            .addResponseHeader("first-response","first-response-header")) // 3 필터등록
                             .uri("http://localhost:8081"))         // 2 : uri 로 라우팅
                // 체이닝으로 등록 가능.
                .route(r -> r.path("/second-service/**")     //1 : patten이  들어오면
                        .filters(f -> f.addRequestHeader("second-request", "second-request-header")
                                .addResponseHeader("second-response","second-response-header")) // 3 필터등록
                        .uri("http://localhost:8082"))         // 2 : uri 로 라우팅
                .build();
    }


}
