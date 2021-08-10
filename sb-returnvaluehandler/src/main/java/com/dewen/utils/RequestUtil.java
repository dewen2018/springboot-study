package com.dewen.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.regex.Matcher;

public class RequestUtil {
    private static final Log logger = LogFactory.getLog(RequestUtil.class);

    /**
     * 获取代理服务器IP.
     *
     * @param request
     * @return
     */
    public static String getProxyIp(HttpServletRequest request) {
        String proxyIp = request.getHeader("X-Real-IP");
        if (proxyIp == null) {
            proxyIp = request.getHeader("RealIP");
        }
        if (proxyIp == null) {
            proxyIp = request.getRemoteAddr();
        }
        return proxyIp;
    }

    public static String getRequestContextUri(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String requestURI;
        if ("/".equals(contextPath)) {
            requestURI = request.getRequestURI();
        } else {
            String uri = request.getRequestURI();
            requestURI = uri.substring(contextPath.length());
        }
        if (requestURI.indexOf("//") != -1) {
            requestURI = requestURI.replaceAll("/+", "/");
        }
        return requestURI;
    }

    public static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attr == null) {
            return null;
        }
        return attr.getRequest();
    }

    /**
     * 判断当前请求是否https.
     *
     * @param request
     * @return
     */
    public static boolean isHttps(HttpServletRequest request) {
        String protocol = request.getProtocol();
        if ("https".equalsIgnoreCase(protocol)) {
            return true;
        }
        boolean isHttps = "true".equals(request.getHeader("isHttps"));
        return isHttps;
    }

    /**
     * 获取域名.
     *
     * @param request
     * @return
     */
    public static String getSchemeAndServerName(HttpServletRequest request) {
        boolean isHttps = isHttps(request);
        StringBuilder sb = new StringBuilder(48);
        int port = request.getServerPort();

        String scheme;
        if (isHttps) {
            scheme = "https";
            if (port == 80) {
                port = 443;
            }
        } else {
            scheme = "http";
        }

        sb.append(scheme);
        sb.append("://");
        sb.append(request.getServerName());
        if (port == 80 && "http".equals(scheme)) {
            //
        } else if (port == 443 && "https".equals(scheme)) {
            //
        } else {
            sb.append(':');
            sb.append(port);
        }
        return sb.toString();
    }

    /**
     * 获取域名.
     *
     * @param request
     * @return
     */
    public static String getDomain(HttpServletRequest request) {
        String domain = "http://" + request.getServerName();
        return domain;
    }

    /**
     * 获取user-agent.
     */
    public static String getUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");
        return userAgent;
    }

    /**
     * 把null或无效的页码转成1.
     *
     * @param pageid
     * @return
     */
    public static int getPageid(Integer pageid) {
        if (pageid == null || pageid <= 0) {
            return 1;
        }
        return pageid;
    }

    /**
     * 打印header信息.
     *
     * @param request
     */
    public static void printHeaders(HttpServletRequest request) {
        Enumeration<String> e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            String value = request.getHeader(name);
            logger.info("header " + name + ":" + value);
        }
    }

    private static final java.util.regex.Pattern IS_VALID_IP_PATTERN = java.util.regex.Pattern.compile("^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$");

    /**
     * 判断IP是否合法.
     *
     * @param ip
     * @return
     */
    public static boolean isValidIp(final String ip) {
        if (ip == null || ip.length() == 0) {
            return false;
        }
        Matcher m = IS_VALID_IP_PATTERN.matcher(ip);
        return m.find();
    }

    /**
     * 打印请求中的对象.
     *
     * @param request
     */
    public static void printAttributes(HttpServletRequest request) {
        Enumeration<String> e = request.getAttributeNames();
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            Object value = request.getAttribute(name);
            logger.info("attribute " + name + ":" + value);
        }
    }

    /**
     * 获取上次访问的地址.
     *
     * @param request
     * @return
     */
    public static String getReferer(HttpServletRequest request) {
        return request.getHeader("referer");
    }

    /**
     * 获取请求参数的值，若不存在则返回默认值.
     *
     * @param request
     * @param parameterName
     * @param defaultValue
     * @return
     */
    public static String getString(HttpServletRequest request, String parameterName, String defaultValue) {
        String value = request.getParameter(parameterName);
        if (value == null || value.length() == 0) {
            return defaultValue;
        } else {
            return value;
        }
        // return StringUtils.isEmpty(value) ? defaultValue : value;
    }

    public static String getRequestURL(HttpServletRequest request) {
        boolean isHttps = "true".equals(request.getHeader("isHttps"));
        StringBuilder sb = new StringBuilder(48);
        int port = request.getServerPort();

        String scheme;
        if (isHttps) {
            scheme = "https";
            if (port == 80) {
                port = 443;
            }
        } else {
            scheme = "http";
        }

        sb.append(scheme);
        sb.append("://");
        sb.append(request.getServerName());
        if (port == 80 && "http".equals(scheme)) {
            //
        } else if (port == 443 && "https".equals(scheme)) {
            //
        } else {
            sb.append(':');
            sb.append(port);
        }
        sb.append(request.getRequestURI());
        return sb.toString();
    }

    /**
     * 是否AJAX请求
     *
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(requestedWith);
    }

    /**
     * 根据path获取第一个目录
     *
     * @param path
     * @return
     */
    public static String getFirstFolder(String path) {
        int index = path.indexOf("/", 1);
        if (index == -1) {
            return null;
        }
        return path.substring(0, index + 1);
    }

}
