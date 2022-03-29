package com.dewen.函数式接口;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Map+函数式接口
 * 用上了Java8的新特性lambda表达式
 * <p>
 * 判断条件放在key中
 * 对应的业务逻辑放在value中
 * 好处是非常直观，能直接看到判断条件对应的业务逻辑
 */

/**
 * 需求：根据优惠券(资源)类型resourceType和编码resourceId查询派发方式grantType
 */
@Service
public class QueryGrantTypeService {

    @Autowired
    private GrantTypeSerive grantTypeSerive;
    private Map<String, Function<String, String>> grantTypeMap = new HashMap<>();
    private Map<String, String> resourceTypeMapId = new HashMap<String, String>() {{
        put("红包", "01d");
        put("购物券", "02d");
        put("qq会员", "03d");
    }};

    /**
     * 初始化业务分派逻辑,代替了if-else部分
     * key: 优惠券类型
     * value: lambda表达式,最终会获得该优惠券的发放方式
     */
    @PostConstruct
    public void dispatcherInit() {
        grantTypeMap.put("红包", resourceId -> grantTypeSerive.redPaper(resourceId));
        grantTypeMap.put("购物券", resourceId -> grantTypeSerive.shopping(resourceId));
        grantTypeMap.put("qq会员", resourceId -> grantTypeSerive.QQVip(resourceId));
    }

    public String getResult(String resourceType) {
        //Controller根据 优惠券类型resourceType、编码resourceId 去查询 发放方式grantType
        Function<String, String> result = grantTypeMap.get(resourceType);
        if (result != null) {
            //传入resourceId 执行这段表达式获得String型的grantType
            // 去查询，这里通过map模拟
//            return result.apply(resourceId);
            return result.apply(resourceTypeMapId.get(resourceType));
        }
        return "查询不到该优惠券的发放方式";
    }
}