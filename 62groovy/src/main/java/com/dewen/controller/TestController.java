package com.dewen.controller;

import com.dewen.config.SpringContextUtil;
import com.dewen.entity.GroovyScript;
import com.dewen.mapper.GroovyScriptMapper;
import com.dewen.service.GroovyService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
@Api(tags = "TestController")
@ApiSupport(order = 1)
public class TestController {


    @ApiOperation(value = "hello")
    @ApiOperationSupport(order = 1)
    @GetMapping("/hello")
    public String test() {
        return "Dewen's demo...";
    }

    @ApiOperation(value = "helloDewen")
    @ApiOperationSupport(order = 2)
    @GetMapping("/helloDewen")
    public void helloDewen() {
        // 创建GroovyShell
        GroovyShell groovyShell = new GroovyShell();
        //装载解析脚本代码
        // Script script = groovyShell.parse("package groovy\n" +
        //         "def HelloWorld(){\n" +
        //         "    println \"hello world\"\n" +
        //         "}");
        Script script = null;
        try {
            // script = groovyShell.parse(new File("./src/main/java/com/dewen/controller/groovy/HelloWord.groovy"));
            File file = ResourceUtils.getFile("classpath:groovy/HelloWord.groovy");
            script = groovyShell.parse(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //执行
        script.invokeMethod("helloWord", null);
        Object[] params1 = new Object[]{10, 5};
        int sum = (int) script.invokeMethod("add", params1);
        System.out.println(sum);

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("科目1", "语文");
        paramMap.put("科目2", "数学");
        Object[] params2 = new Object[]{paramMap};
        String result = (String) script.invokeMethod("mapToString", params2);
        System.out.println("mapToString:" + result);


        script.invokeMethod("getBean", null);
    }

    @ApiOperation(value = "helloDewen2")
    @ApiOperationSupport(order = 3)
    @GetMapping("/helloDewen2")
    public void helloDewen2() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("groovy/spring-groovy.xml");
        GroovyService bean = context.getBean(GroovyService.class);
        String sayHello = bean.sayHello();
        // String sayHello = bean.sayHello("dewen");
        System.out.println(sayHello);
    }

    @Autowired
    GroovyScriptMapper groovyScriptMapper;

    @ApiOperation(value = "helloDewen3")
    @ApiOperationSupport(order = 4)
    @GetMapping("/helloDewen3")
    public void helloDewen3() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        GroovyScript groovyScript = this.groovyScriptMapper.getOne(1L);
        Class clazz = new GroovyClassLoader().parseClass(groovyScript.getScriptContent());
        // 弃用了
        // Object o = clazz.newInstance();
        Object o = clazz.getDeclaredConstructor().newInstance();
        SpringContextUtil.autowireBean(o);
        Method method = clazz.getMethod("sayHello");
        String res = (String) method.invoke(o);
        // Method method = clazz.getMethod("sayHello", String.class);
        // String res = (String) method.invoke(o, "dewen");
        System.out.println(res);
    }
}
