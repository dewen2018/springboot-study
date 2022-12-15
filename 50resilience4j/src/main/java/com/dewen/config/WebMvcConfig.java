// package com.dewen.config;
//
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.reactive.config.CorsRegistry;
// import org.springframework.web.reactive.config.ResourceHandlerRegistry;
// import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
// @Configuration
// public class WebMvcConfig extends WebMvcConfigurationSupport {
//     //这个和swagger没有关系，是配置跨域的
//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/**")
//                 .allowedOrigins("*")
//                 .allowedMethods("GET", "POST","DELETE","OPTIONS")
//                 .allowCredentials(false).maxAge(3600);
//     }
//     @Override
//     public void addResourceHandlers(ResourceHandlerRegistry registry) {
//         registry.
//                 addResourceHandler("/swagger-ui/**")
//                 .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
//                 .resourceChain(false);
//         super.addResourceHandlers(registry);
//     }
//
//     @Override
//     public void addViewControllers(ViewControllerRegistry registry) {
//         registry.addViewController("/swagger-ui/")
//                 .setViewName("forward:/swagger-ui/index.html");
//     }
// }
