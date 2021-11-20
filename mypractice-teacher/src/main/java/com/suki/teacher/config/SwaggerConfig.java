package com.suki.teacher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.suki.teacher.controller"))
                //.paths(PathSelectors.ant("/swagger/**"))
                .build();

    }

    private ApiInfo webApiInfo(){
        return new ApiInfo("suki文档",
                "suki描述",
                          "v1.0",
                "https://github.com/Suqing999",
                new Contact("suki","https://github.com/Suqing999","suki@qq.com"),
                "Apache2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
