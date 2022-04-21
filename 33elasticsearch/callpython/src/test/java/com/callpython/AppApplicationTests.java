package com.callpython;

import org.junit.jupiter.api.Test;
import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.jline.internal.InputStreamReader;
import org.python.util.PythonInterpreter;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;

@SpringBootTest
class AppApplicationTests {

    @Test
    void contextLoads() {
    }


    /**
     * Java 类中直接执行 Python 语句
     */
    @Test
    void executePython() {
        String py = "print('Hello world!')";

        PythonInterpreter interpreter = new PythonInterpreter();
        // 执行 Python 语句
        interpreter.exec(py);
    }

    /**
     * 调用 Python 脚本文件
     */
    @Test
    void executePythonFile() {
        // 定义脚本路径
        String path = "testpython.py";

        PythonInterpreter interpreter = new PythonInterpreter();
        // 执行脚本文件
        interpreter.execfile(path);
    }

    /**
     * Runtime.getRuntime() 执行 python 脚本文件
     */
    @Test
    void executePythonFile2() {
        Process proc;
        BufferedReader reader;
        try {
            // 直接执行 python 命令的方式来执行脚本
            proc = Runtime.getRuntime().exec("python testpython.py");
            reader = new BufferedReader(new InputStreamReader(proc.getInputStream(), "gbk"));

            String line = null;
            while ((line = reader.readLine()) != null) {
                // 打印输出
                System.out.println(line);
            }

            reader.close();
            proc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用 Python 脚本调用某个函数，并动态传入参数
     */
    @Test
    void executePythonFunction() throws UnsupportedEncodingException {
        PythonInterpreter interpreter = new PythonInterpreter();
        // 指定指定路径下的 python 脚本
        interpreter.execfile("testpython2.py");

        // 指定需要调用的函数
        PyFunction function = interpreter.get("sendEmail", PyFunction.class);
        // ↓↓↓↓↓↓↓↓ 需要传入的参数 ↓↓↓↓↓↓↓↓
        // 邮件接受者
        PyString receiver = Py.newStringOrUnicode("xxx@qq.com");
        // 邮件主题
        PyString subject = Py.newStringOrUnicode("打个招呼【动态参数】");
        // 邮件内容
        PyString content = Py.newStringOrUnicode("我是犬小哈【动态参数】");

        // 调用
        PyObject pyObject = function.__call__(receiver, subject, content);

        // 设置对返回的中文解码，在python中不需要设置中文编码方式
        String result = new String(pyObject.toString().getBytes("iso8859-1"), "utf-8");

        // 打印返回结果
        System.out.println(String.format("result: %s", result));
    }
}
