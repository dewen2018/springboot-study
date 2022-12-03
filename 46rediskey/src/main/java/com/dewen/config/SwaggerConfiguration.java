package com.dewen.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
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
//增强扫描
@EnableKnife4j
// @ComponentScan(
//         basePackages = {
//                 "com.github.xiaoymin.knife4j.spring.plugin",
//                 "com.github.xiaoymin.knife4j.spring.web"
//         }
// )
public class SwaggerConfiguration {
    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public SwaggerConfiguration(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean
    public Docket defaultApi() {
        return new Docket(DocumentationType.OAS_30)
                // .host()
                .apiInfo(groupApiInfo())
                .groupName("默认分组接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dewen.controller"))
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions("doc-knife4j-1.0.0"));
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

    // /**
    //  * 增加如下配置可解决Spring Boot 6.x 与Swagger 3.0.0 不兼容问题
    //  **/
    // @Bean
    // public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier, ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier, EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties, Environment environment) {
    //     List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
    //     Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
    //     allEndpoints.addAll(webEndpoints);
    //     allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
    //     allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
    //     String basePath = webEndpointProperties.getBasePath();
    //     EndpointMapping endpointMapping = new EndpointMapping(basePath);
    //     boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
    //     return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
    // }
    //
    // private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
    //     return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    // }
}