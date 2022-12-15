package com.dewen.config;

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

@Configuration
@EnableSwagger2

public class SwaggerConfiguration {


    @Bean
    public Docket defaultApi() {
        return new Docket(DocumentationType.OAS_30)
                // .host()
                .apiInfo(groupApiInfo())
                .groupName("默认接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dewen.controller"))
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo groupApiInfo() {
        Contact contact = new Contact("Dewen", "dewem.com", "group@qq.com");
        return new ApiInfoBuilder()
                .title("Dewen's project demo")
                .description("swagger-bootstrap-ui-demo RESTful APIs")
                .termsOfServiceUrl("https://dewen2018.github.io/")
                .contact(contact)
                .version("1.0")
                .build();
    }
}