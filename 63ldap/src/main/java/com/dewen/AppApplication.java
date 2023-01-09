package com.dewen;

import io.leopard.javahost.JavaHost;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.net.InetAddress;
import java.util.Properties;

@SpringBootApplication
public class AppApplication {
    private static Logger logger = LoggerFactory.getLogger(AppApplication.class);

    @SneakyThrows
    public static void main(String[] args) {
        // SpringApplication.run(AppApplication.class, args);
        // SpringApplication app = new SpringApplication(AppApplication.class);
        // ConfigurableApplicationContext application = app.run(args);
        ConfigurableApplicationContext cac = SpringApplication.run(AppApplication.class, args);
        Environment env = cac.getEnvironment();
        logger.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}{}\n\t" +
                        "External: \thttp://{}:{}{}\n\t" +
                        "Doc: \thttp://{}:{}{}/doc.html\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                env.getProperty("server.servlet.context-path"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getProperty("server.servlet.context-path"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getProperty("server.servlet.context-path"));
        // java8环境支持
        Resource resource = new ClassPathResource("vdns.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        JavaHost.updateVirtualDns(props);
        JavaHost.printAllVirtualDns();
    }

}
