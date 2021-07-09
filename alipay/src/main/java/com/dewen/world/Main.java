package com.dewen.world;

import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayOpenOperationOpenbizmockBizQueryModel;
import com.alipay.api.request.AlipayOpenOperationOpenbizmockBizQueryRequest;
import com.alipay.api.response.AlipayOpenOperationOpenbizmockBizQueryResponse;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. 创建AlipayClient实例
            AlipayClient alipayClient = new DefaultAlipayClient(getClientParams());
            // 2. 创建使用的Open API对应的Request请求对象
            AlipayOpenOperationOpenbizmockBizQueryRequest request = getRequest();
            // 3. 发起请求并处理响应
            AlipayOpenOperationOpenbizmockBizQueryResponse response = alipayClient.certificateExecute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功。");
            } else {
                System.out.println("调用失败，原因：" + response.getMsg() + "，" + response.getSubMsg());
            }
        } catch (Exception e) {
            System.out.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static CertAlipayRequest getClientParams() {
        CertAlipayRequest certParams = new CertAlipayRequest();
        certParams.setServerUrl("https://openapi.alipaydev.com/gateway.do");
        //请更换为您的AppId
        certParams.setAppId("2016101600703797");
        //请更换为您的PKCS8格式的应用私钥
        certParams.setPrivateKey("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCMiVPTDltgVkoehVZCALHqBnoVsxWaKRXsLziTvTX4UH0S7CuktYfGkDiVG0MZ47eQdFQhGyAgeKFNkaeLBC0xws4+CCpHBkvXhrOvtwv/mK8VFoE17Uo+Rgd8ppEtCFxcQ1gH9ECESH/6nQ5PhjvYGP+o27Mf0yHhzBcisAKY4v9KKIfqeVS5TIWzOjQBDwyF8Mp133kEbWI5LRcx93dItAr5Ask8NEZrCFmCnRiE5ZVfpmOU6ZsDuBLMK5LeNmn5JShExggBao42jkUzV+ByuFu4SsbkyIFFNsob9EL4aXLF8+99NIVacSfzPUaeK7TEEy0pMhWnyoicN5OEsLSVAgMBAAECggEAKpVJ8v+8rFo5JzpbwrAXOMQxpfWXrBia7e/5ArsoaU9qNpudNdFTjNUScAvMr0GS05CMFWP0viIPLkPeMn9XemZ+u8kZll/QB0RnmeyrFA2dr5QcHs0B3BC4IAsQnkaURdiaCf72tZ6KONPyDCKua3JXtUCs7A27jIGc2QPgJZbc1BT1Gt2fY5D1dq4xfNk4tWHJG8dwkKECVnUCu8Yzokbs4TkoQ46uzGAe38GkGYapUgZnzGo7+RJAscY2lm33icjk8doG1DP2yf2cKwAvvspXprNR35EBSFAd24xPB4v+d/OLioYHxKPvFemvCZDbi4AEOOpzcPsR193vLubaQQKBgQDtKCp28JVZnT/2etZEDBgS9oT6++3AvdcX0dd+a6UjYn86o5uU6V7YBR92PEna/lhBT3Mstr4AkDryVc6x9igg4AtQoIMMrSm4CwBcfL2VazZzkV5cozsCzZYiVsHFvx+W6/U731eRY9yZbM0ExeoF5PeCdXG0b5YKAeQ2b39ifQKBgQCXs+AxuRAD3Eh3TFnUBTmG0V4qNmlMFtEJq1UrPPaSzcG6pexUNT+TVOGMnpLtJQ52/Y/76s244lPGF6ZhogwykR2STSdpMBHFDP8LBc2/F5G0x1vkxAeKQkpHp8bPyHLaiY02DWFe+uPRQPF4Oxw0u4h5ksrEBw5TMqeiiBTd+QKBgEMOv2sgPeq39xUXyKw3CzuZbyKUx7ZfN4mpsRgzEd++gyEuj9h8YHAOisXg6DU/A+97qqJzDaiVH8XUE3zokJIzJ5/lswiLkidTHQgJ0YWFakElUpw5H398aD5Pb70dfK4tDAug7F7U8NSFGjeGStTd2EqQMNWHU9KClGf8APX1AoGAKO39JS07Ju9+FcHh0pR+SuOeNmFr032sdKFnXz0Zgc5F4Kf3tViQiJ20JQ57scP3vR74hA1759AYri+fUmQ4SkRg6CIs6fJwOOwiu8SbGF2xkEbhgj1pk2IDTIX13AXHBzNpiBXgGZIzeTe+bqP0Hj4iujpivrTwpn3aQL3ac3ECgYEA5T7aOthLEKLxkcq6r4WjD5O66elmGttZjGEhqxdAVjVzmZfbgF3vKyX/8fvyAldHhdp1L1MtAxD4TXXrfGBWMIY4v+ExzLNo0Y37bQL90qp4kubWydGWgZpFlbFxJNxoq/1chKfJJN24/fQ9Adn3ik+PJp/rr74bUKbdNSH6v+s=");
        //请更换为您使用的字符集编码，推荐采用utf-8
        certParams.setCharset("utf-8");
        certParams.setFormat("json");
        certParams.setSignType("RSA2");
        //请更换为您的应用公钥证书文件路径
        certParams.setCertPath("C:/Users/86357/Desktop/支付宝沙箱环境/appCertPublicKey_2016101600703797.crt");
        //请更换您的支付宝公钥证书文件路径
        certParams.setAlipayPublicCertPath("C:/Users/86357/Desktop/支付宝沙箱环境/alipayCertPublicKey_RSA2.crt");
        //更换为支付宝根证书文件路径
        certParams.setRootCertPath("C:/Users/86357/Desktop/支付宝沙箱环境/alipayRootCert.crt");
        return certParams;
    }

    private static AlipayOpenOperationOpenbizmockBizQueryRequest getRequest() {
        // 初始化Request，并填充Model属性。实际调用时请替换为您想要使用的API对应的Request对象。
        AlipayOpenOperationOpenbizmockBizQueryRequest request = new AlipayOpenOperationOpenbizmockBizQueryRequest();
        AlipayOpenOperationOpenbizmockBizQueryModel model = new AlipayOpenOperationOpenbizmockBizQueryModel();
        model.setBizNo("test");
        request.setBizModel(model);
        return request;
    }
}
