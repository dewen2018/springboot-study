package com.dewen;


import io.undertow.UndertowOptions;
import io.undertow.servlet.api.SecurityConstraint;
import io.undertow.servlet.api.SecurityInfo;
import io.undertow.servlet.api.TransportGuaranteeType;
import io.undertow.servlet.api.WebResourceCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 支持http
 */
@Configuration
public class Http2Config {

    @Value("${server.http.port}")
    private Integer httpport;

    @Value("${server.port}")
    private Integer httpsport;

    /**
     * 采用undertow作为服务器。
     * undertow是一个用java编写的、灵活的、高性能的web服务器，提供基于nio的阻塞和非阻塞api，特点：
     * 非常轻量级，undertow核心瓶子在1mb以下。它在运行时也是轻量级的，有一个简单的嵌入式服务器使用少于4mb的堆空间。
     * 支持http升级，允许多个协议通过http端口进行多路复用。
     * 提供对web套接字的全面支持，包括jsr-356支持。
     * 提供对servlet 3.1的支持，包括对嵌入式servlet的支持。还可以在同一部署中混合servlet和本机undertow非阻塞处理程序。
     * 可以嵌入在应用程序中或独立运行，只需几行代码。
     * 通过将处理程序链接在一起来配置undertow服务器。它可以对各种功能进行配置，方便灵活。
     */
    @Bean
    public ServletWebServerFactory undertowFactory() {
        UndertowServletWebServerFactory undertowFactory = new UndertowServletWebServerFactory();
        // UndertowBuilderCustomizer undertowBuilderCustomizer = new UndertowBuilderCustomizer() {
        //     @Override
        //     public void customize(Undertow.Builder builder) {
        //         builder.addHttpListener(httpport, "0.0.0.0");
        //         // 开启http2
        //         builder.setServerOption(UndertowOptions.HTTP2_SETTINGS_ENABLE_PUSH, true);
        //     }
        // };
        // undertowFactory.addBuilderCustomizers(undertowBuilderCustomizer);
        undertowFactory.addBuilderCustomizers(builder -> {
            builder.addHttpListener(httpport, "0.0.0.0")
                    // 开启http2
                    .setServerOption(UndertowOptions.ENABLE_HTTP2, true)
                    // 开启HTTP2以及server push功能
                    .setServerOption(UndertowOptions.HTTP2_SETTINGS_ENABLE_PUSH, true);
        });

        undertowFactory.addDeploymentInfoCustomizers(deploymentinfo -> {
            // 开启http自动跳转至https
            deploymentinfo.addSecurityConstraint(new SecurityConstraint()
                    .addWebResourceCollection(new WebResourceCollection().addUrlPattern("/*"))
                    .setTransportGuaranteeType(TransportGuaranteeType.CONFIDENTIAL)
                    .setEmptyRoleSemantic(SecurityInfo.EmptyRoleSemantic.PERMIT))
                    .setConfidentialPortManager(exchange -> httpsport);
        });
        return undertowFactory;
    }
}