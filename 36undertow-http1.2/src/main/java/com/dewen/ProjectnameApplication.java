package com.dewen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@RestController
@SpringBootApplication
public class ProjectnameApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectnameApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello dewen!";
    }


    @GetMapping("/demo")
    public void http2ServerPush(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PushBuilder pushBuilder = request.newPushBuilder();
        pushBuilder.path("/1.jpg")
                .addHeader("content-type", "image/png")
                .push();

        try (PrintWriter respWriter = response.getWriter()) {
            respWriter.write("<html>" +
                    "<img src='/1.jpg'>" +
                    "</html>");
        }
    }

    @GetMapping(value = "/1.jpg")
    public void download(HttpServletResponse response) throws IOException {
        InputStream data = getClass().getClassLoader().getResourceAsStream("1.jpg");
        response.setHeader("content-type", "image/png");
        FileCopyUtils.copy(data, response.getOutputStream());
    }
}
