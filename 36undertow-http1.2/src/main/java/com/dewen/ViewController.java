package com.dewen;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 使用push
 */
@Controller
public class ViewController {

    @GetMapping("/index")
    public void index(HttpServletRequest request, HttpServletResponse response) {
        PushBuilder pushBuilder = request.newPushBuilder();

        if (pushBuilder != null) {
            pushBuilder.path("css/style.css")
                    // .addHeader("content-type", "text/css")
                    .push();

            pushBuilder.path("image/2.jpg")
                    // .addHeader("content-type", "image/jpeg")
                    .push();

            pushBuilder.path("image/favicon.ico")
                    // .addHeader("content-type", "image/png")
                    .push();

        }

        try (PrintWriter respWriter = response.getWriter()) {
            String viewHTML = "index.html";

            respWriter.write("<!DOCTYPE html>" +
                    "<html lang='en'>" +
                    "<head>" +
                    "    <meta charset='UTF-8'>" +
                    "    <title>Dewen</title>" +
                    "    <link rel='stylesheet' href='css/style.css'>" +
                    "    <link rel='shortcut icon' href='image/favicon.ico'/>" +
                    "</head>" +
                    "<body>" +
                    "<img src='image/2.jpg'>" +
                    "</body>" +
                    "</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/index2")
    // public String index2(HttpServletRequest request) {
    //     PushBuilder pushBuilder = request.newPushBuilder();
    //     if (pushBuilder != null) {
    //         pushBuilder.path("css/*.*").push();
    //         pushBuilder.path("image/*.*").push();
    //     }
    //     String viewHTML = "index2.html";
    //     return viewHTML;
    // }
    public String index2(HttpServletRequest request) {
        PushBuilder pushBuilder = request.newPushBuilder();
        if (pushBuilder != null) {
            pushBuilder.path("css/style.css").push();
            pushBuilder.path("image/d.webp").push();
            pushBuilder.path("image/d2.webp").push();
            pushBuilder.path("image/d3.webp").push();
            pushBuilder.path("image/favicon.ico").push();
        }
        String viewHTML = "index2.html";
        return viewHTML;
    }
    // public ModelAndView index2(HttpServletRequest request) {
    //     PushBuilder pushBuilder = request.newPushBuilder();
    //     if (pushBuilder != null) {
    //         pushBuilder.path("css/style.css").push();
    //         pushBuilder.path("image/d.webp").push();
    //         pushBuilder.path("image/d2.webp").push();
    //         pushBuilder.path("image/d3.webp").push();
    //         pushBuilder.path("image/favicon.ico").push();
    //     }
    //     String viewHTML = "index2.html";
    //     ModelAndView mav = new ModelAndView(viewHTML);
    //     return mav;
    // }

}
