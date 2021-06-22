package com.dewen;

import com.alibaba.fastjson.JSONObject;
import com.dewen.consts.ReqConst;
import com.dewen.entity.FsDepartment;
import com.dewen.utils.HttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class FeishumsgApplicationTests {



    @Test
    void contextLoads() {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("parent_department_id", 0);
        JSONObject res = HttpUtil.get(ReqConst.DEPARTMENTS_LIST, paramsMap);
        if (0 == res.getInteger("code") && "success".equals(res.get("msg"))) {
            JSONObject _obj = res.getJSONObject("data");
            List<FsDepartment> fsDepartments = JSONObject.parseArray(_obj.getString("items"), FsDepartment.class);

            System.out.println(fsDepartments);
        }
    }

}
