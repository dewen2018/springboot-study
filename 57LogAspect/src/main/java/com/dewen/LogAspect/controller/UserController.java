package com.dewen.LogAspect.controller;

import com.dewen.LogAspect.annotation.SystemControllerLog;
import com.dewen.LogAspect.model.ExecutionResult;
import com.dewen.LogAspect.model.User;
import com.dewen.LogAspect.service.impl.UserService;
import com.dewen.LogAspect.utils.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 访问路径 http://localhost:11000/user/findUserNameByTel?tel=1234567
     * @param tel 手机号
     * @return userName
     */
    @RequestMapping("/findUserNameByTel")
    public String findUserNameByTel(@RequestParam("tel") String tel){
        return userService.findUserName(tel);
    }




    @RequestMapping(value = "/userlist")
    @SystemControllerLog(descrption = "查询用户信息",actionType = "4")
    public ExecutionResult getUserList(@RequestParam("id") String id) throws Exception{

        ExecutionResult result = new ExecutionResult();
        try {
            List<User> users = userService.findAll() ;
            result.setTotal(users.size());
            result.setResultCode(ReturnCode.RES_SUCCESS);
            result.setFlag(true);
            result.setData(users);
            result.setMsg("查询成功！");
            //异常处理
//            int aa= 5/0;
        }catch (Exception e){
            result.setFlag(true);
            result.setData(null);
            result.setResultCode(ReturnCode.RES_FAILED);
            result.setMsg("查询失败！");
            throw e ;
        }
        return result ;
    }
}