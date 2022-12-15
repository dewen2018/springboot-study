package com.dewen;

import com.thebeastshop.forest.springboot.annotation.ForestScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ForestScan(basePackages = {"com.dewen.client","com.dewen.client2"})
@SpringBootApplication
public class ForestClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForestClientApplication.class, args);
    }


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dewen.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Dewen Title")
                .contact(new Contact("Dewen", "https://dewen.com", "863572313@qq.com"))
//                .contact(new Contact("DtFlys", "https://gitee.com/dt_flys", "dt_flys@hotmail.com"))
                .version("1.0")
                .description("Forest示例工程中的Demo接口")
                .build();
    }
}
