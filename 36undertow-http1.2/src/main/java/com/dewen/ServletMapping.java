package com.dewen;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 测试没发现怎么用
 */
@Slf4j
@WebServlet({"/path/*", "*.ext"})
public class ServletMapping extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpServletMapping mapping = request.getHttpServletMapping();
        // String mapping = mapping.getMappingMatch().name();
        log.info(mapping.getMappingMatch().name());
        String value = mapping.getMatchValue();
        log.info(value);
        String pattern = mapping.getPattern();
        log.info(pattern);
        String servletName = mapping.getServletName();
        log.info(servletName);
    }

}