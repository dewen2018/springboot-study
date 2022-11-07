package com.dewen;

import com.dewen.enums.OrderEnum;
import com.power.common.util.DateTimeUtil;
import com.power.doc.builder.HtmlApiDocBuilder;
import com.power.doc.model.*;
import com.power.doc.model.rpc.RpcApiDependency;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        ApiConfig config = new ApiConfig();
        // 服务地址
        config.setServerUrl("http://localhost:8080");
        // 采用严格模式
        config.isStrict();
        config.setOpenUrl("http://localhost:8080/api");
        config.setAppToken("token_xxxxxxxxxxxxxxxxxxxxx");
        config.setDebugEnvName("测试环境");
        config.setInlineEnum(true);
        config.setStyle("randomLight");
        config.setCreateDebugPage(true);
        config.setAuthor("dewen");
        config.setDebugEnvUrl("http://127.0.0.1");
        //config.setTornaDebug(true);
        // 生成一个文档
        config.setAllInOne(true);
        // 文档输出路径 (对应当前项目所在的磁盘目录)
        config.setOutPath("./wnm");
        config.setMd5EncryptedHtmlName(true);
        //不指定SourcePaths默认加载代码为项目src/main/java下的
        config.setSourceCodePaths(
                // SourceCodePath.builder().setDesc("本项目代码").setPath("C:\\Users\\xingzi\\Desktop\\smart-doc-example-cn")
                // SourcePath.path().setPath("F:\\Personal\\project\\smart\\src\\main\\java")
                // SourcePath.path().setDesc("加载项目外代码").setPath("E:\\ApplicationPower\\ApplicationPower\\Common-util\\src\\main\\java")
        );
        // config.setPackageFilters("com.dewen.*");

        config.setDataDictionaries(
                ApiDataDictionary.builder().setTitle("订单字典").setEnumClass(OrderEnum.class).setCodeField("code").setDescField("desc")
        );
        // 添加请求的header
        //设置请求头，如果没有请求头，可以不用设置
        config.setRequestHeaders(
                ApiReqParam.builder().setName("access_token").setType("string").setDesc("Basic auth credentials"),
                ApiReqParam.builder().setName("user_uuid").setType("string").setDesc("User Uuid key")
        );
        //对于外部jar的类，api-doc目前无法自动获取注释，
        //如果有这种场景，则自己添加字段和注释，api-doc后期遇到同名字段则直接给相应字段加注释
        // config.setCustomResponseFields(
        //         CustomRespField.field().setName("success").setDesc("成功返回true,失败返回false"),
        //         CustomRespField.field().setName("message").setDesc("接口响应信息"),
        //         CustomRespField.field().setName("data").setDesc("接口响应数据"),
        //         CustomField.builder().setName("msg").setDesc("消息测试").setIgnore(true).setValue("000200"),
        //         CustomField.builder().setName("code2").setDesc("code测试").setIgnore(false).setValue("010000")
        //         //.setDesc("响应代码")
        // );
        // config.setCustomRequestFields(
        //         CustomField.builder()
        //                 .setName("age").setDesc("年龄").setIgnore(false).setValue("13").setRequire(false).setOwnerClassName("com.power.doc.entity.SimpleUser"),
        //         CustomField.builder()
        //                 .setName("sex").setDesc("性别").setIgnore(false).setValue("男").setRequire(true).setOwnerClassName("com.power.doc.entity.SimpleUser")
        // );
        // 非必须只有当setAllInOne设置为true时文档变更记录才生效，https://gitee.com/sunyurepository/ApplicationPower/issues/IPS4O
        config.setRevisionLogs(
                RevisionLog.builder().setRevisionTime("2022/10/15").setAuthor("dewendu").setRemarks("测试").setStatus("创建").setVersion("V1.0.0"),
                RevisionLog.builder().setRevisionTime("2022/10/16").setAuthor("dewendu").setRemarks("测试2").setStatus("修改").setVersion("V1.0.1")
        );

        config.setResponseBodyAdvice(BodyAdvice.builder()
                .setDataField("data")
                .setDataField("dadada")
                .setClassName("com.power.common.model.CommonResult"));

        config.setRequestBodyAdvice(BodyAdvice.builder()
                .setDataField("data")
                .setDataField("dadada")
                .setClassName("com.power.common.model.CommonResult"));

        // config.setRpcApiDependencies(RpcApiDependency.builder().setGroupId("com.test").setArtifactId("test1").setVersion("1.0"),
        //         RpcApiDependency.builder().setGroupId("com.smart").setArtifactId("test").setVersion("1.1.1")
        // );

        long start = System.currentTimeMillis();

        // TornaBuilder.buildApiDoc(config);
        // SwaggerBuilder.buildOpenApi(config);
        // RpcMarkdownBuilder.buildApiDoc(config);
        HtmlApiDocBuilder.buildApiDoc(config);
        // RpcTornaBuilder.buildApiDoc(config);
        // TornaBuilder.buildApiDoc(config);
        // RpcTornaBuilder.buildApiDoc(config);
        //TornaBuilder.buildApiDoc(config);
        // RpcHtmlBuilder.buildApiDoc(config);
        long end = System.currentTimeMillis();
        DateTimeUtil.printRunTime(end, start);
    }

    @Test
    public void test_1() {
        ApiConfig config = new ApiConfig();
        config.setServerUrl("http://127.0.0.1:8899");
        config.setOpenUrl("http://localhost:7700/api");
        config.setAppToken("be4211613a734b45888c075741680e49");

        config.setDebugEnvName("测试环境");
        config.setStyle("randomLight");
        config.setCreateDebugPage(true);
        config.setDebugEnvUrl("http://127.0.0.1");

        config.setCreateDebugPage(false);
        config.setAllInOne(true);
        config.setOutPath("D:\\md3_vector");
        config.setMd5EncryptedHtmlName(true);
        //不指定SourcePaths默认加载代码为项目src/main/java下的
        config.setSourceCodePaths(
                SourceCodePath.builder().setDesc("本项目代码").setPath("F:\\IDEA\\IdeaProject\\seckill\\src\\main\\java\\com\\fht\\seckill\\controller")
        );
        //config.setPackageFilters("com.power.doc.dubbo.*");
        //config.setPackageFilters("com.fht.seckill.controller.*");

        config.setDataDictionaries(
                ApiDataDictionary.builder().setTitle("订单字典").setEnumClass(OrderEnum.class).setCodeField("code").setDescField("desc")
        );
        //设置请求头，如果没有请求头，可以不用设置
     /*   config.setRequestHeaders(
                ApiReqHeader.header().setName("access_token").setType("string").setDesc("Basic auth credentials"),
                ApiReqHeader.header().setName("user_uuid").setType("string").setDesc("User Uuid key")
        );*/
        //对于外部jar的类，api-doc目前无法自动获取注释，
        //如果有这种场景，则自己添加字段和注释，api-doc后期遇到同名字段则直接给相应字段加注释
        config.setCustomResponseFields(
//                CustomRespField.field().setName("success").setDesc("成功返回true,失败返回false"),
//                CustomRespField.field().setName("message").setDesc("接口响应信息"),
//                CustomRespField.field().setName("data").setDesc("接口响应数据"),
                CustomField.builder().setName("msg").setDesc("消息测试").setIgnore(true).setValue("000200"),
                CustomField.builder().setName("code2").setDesc("code测试").setIgnore(false).setValue("010000")
                //.setDesc("响应代码")
        );
        //非必须只有当setAllInOne设置为true时文档变更记录才生效，https://gitee.com/sunyurepository/ApplicationPower/issues/IPS4O
        config.setRevisionLogs(
                RevisionLog.builder().setRevisionTime("2018/12/15").setAuthor("chen").setRemarks("测试").setStatus("创建").setVersion("V1.0"),
                RevisionLog.builder().setRevisionTime("2018/12/16").setAuthor("chen2").setRemarks("测试2").setStatus("修改").setVersion("V2.0")
        );
        config.setRpcApiDependencies(RpcApiDependency.builder().setGroupId("com.test").setArtifactId("test1").setVersion("1.0"),
                RpcApiDependency.builder().setGroupId("com.smart").setArtifactId("test").setVersion("1.1.1")
        );
        long start = System.currentTimeMillis();

        HtmlApiDocBuilder.buildApiDoc(config);
        long end = System.currentTimeMillis();
        DateTimeUtil.printRunTime(end, start);
    }

}
