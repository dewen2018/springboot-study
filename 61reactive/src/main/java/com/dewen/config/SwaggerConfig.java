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
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@Configuration
public class SwaggerConfig {
    // private final OpenApiExtensionResolver openApiExtensionResolver;
    //
    // @Autowired
    // public SwaggerConfig(OpenApiExtensionResolver openApiExtensionResolver) {
    //     this.openApiExtensionResolver = openApiExtensionResolver;
    // }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dewen.controller"))
                // .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
        // .extensions(openApiExtensionResolver.buildExtensions("doc-knife4j-1.0.0"))
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Dewen's webflux reactive")
                .description("Webflux Reactive Framwork Example RESTful APIs")
                .contact(new Contact("Dewen", "https://dewen2018.github.io", "group@qq.com"))
                .version("1.0")
                .build();
    }

}
