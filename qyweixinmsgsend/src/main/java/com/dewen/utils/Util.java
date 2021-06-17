package com.dewen.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dewen.entity.WeixinDepartment;
import com.dewen.entity.WeixinUser;

import java.util.ArrayList;
import java.util.List;

public class Util {

    private static final String chatid = "wrHKTcDwAAcaVf3oSmrkYxkmIPRydbnw";
    private static final String token = "yVdyT0hYViH5_ywj0TNp2CDdrKM6GiaQZVku57JSrATIDntFg8f-njWgekJKj0XRCtF_1ANS0qzuB4QPEfGOsS0bw9kPNjpRHqRtNCbSCMJVP4axvtQK_qckaYWs2Rl9qjmJrXJXYMbafd_TygKEdedfR465qwOE2nXyE0NjBsIlWESsfMAg8HjA2wnORNHIf_sVABhZLxVv1uT3cW9lQg";

    private static final String corpId = "wwd769544d44427805";
    private static final String corpsecret = "O3jsCFdVtW4VAluRahzNbRPM_zKolCPJE8XuksJqNtE";
    // 获取token
    private static final String getTokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s";
    //读取成员信息
    private static final String getUserUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID";

    /**
     * 获取部门列表
     * access_token	是	调用接口凭证
     * id &id=%s	否	部门id。获取指定部门及其下的子部门（以及及子部门的子部门等等，递归）。 如果不填，默认获取全量组织架构
     */
    private static final String departmentlistUrl = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=%s";

    /**
     * 获取部门成员
     * access_token	是	调用接口凭证
     * department_id	是	获取的部门id
     * fetch_child	否	是否递归获取子部门下面的成员：1-递归获取，0-只获取本部门
     */
    private static final String simplelistUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=%s&department_id=%s&fetch_child=%s";

    /**
     * 获取token
     *
     * @return
     */
    public static String getToken() {
        String res = HttpUtil.get(String.format(getTokenUrl, corpId, corpsecret));
        JSONObject jsonObject = JSONUtil.parseObj(res);
        if ("0".equals(jsonObject.getStr("errcode")))
            return jsonObject.getStr("access_token");
        return null;
    }

    /**
     * 获取部门列表
     */
    public static List<WeixinDepartment> getDepartmentList() {
        String res = HttpUtil.get(String.format(departmentlistUrl, token));
        JSONObject jsonObject = JSONUtil.parseObj(res);
        if ("0".equals(jsonObject.getStr("errcode"))) {
            List<WeixinDepartment> weixinDepartments = JSONUtil.toList(jsonObject.getStr("department"), WeixinDepartment.class);
            return weixinDepartments;
        }
        return new ArrayList<WeixinDepartment>();
    }

    /**
     * 获取部门成员
     */
    public static List<WeixinUser> getUserList() {
        List<WeixinUser> weixinUsers = new ArrayList<>();
        List<WeixinDepartment> weixinDepartments = getDepartmentList();
        for (WeixinDepartment weixinDepartment : weixinDepartments) {
            String res = HttpUtil.get(String.format(simplelistUrl, token, weixinDepartment.getId(), 1));
            JSONObject jsonObject = JSONUtil.parseObj(res);
            if ("0".equals(jsonObject.getStr("errcode"))) {
                weixinUsers.addAll(JSONUtil.toList(jsonObject.getStr("userlist"), WeixinUser.class));
            }
        }
        return weixinUsers;
    }

    /**
     * 创建群聊会话
     * access_token	是	调用接口凭证
     * name	否	群聊名，最多50个utf8字符，超过将截断
     * owner	否	指定群主的id。如果不指定，系统会随机从userlist中选一人作为群主
     * userlist	是	群成员id列表。至少2人，至多2000人
     * chatid	否	群聊的唯一标志，不能与已有的群重复；字符串类型，最长32个字符。只允许字符0-9及字母a-zA-Z。如果不填，系统会随机生成群id
     */
    private static final String appchatcreateUrl = "https://qyapi.weixin.qq.com/cgi-bin/appchat/create?access_token=%s";

    /**
     * {
     * "name" : "NAME",
     * "owner" : "userid1",
     * "userlist" : ["userid1", "userid2", "userid3"],
     * "chatid" : "CHATID"
     * }
     *
     * @param useridlist
     * @return
     */
    public static String appchatCreate(String ownerId, List<String> useridlist) {
        JSONObject paramMap = new JSONObject();
        paramMap.put("name", "测试群聊");
        paramMap.put("owner", ownerId);
        paramMap.put("userlist", useridlist);
        String res = HttpUtil.post(String.format(appchatcreateUrl, token), paramMap.toString());
        JSONObject jsonObject = JSONUtil.parseObj(res);
        if ("0".equals(jsonObject.getStr("errcode"))) {
            return jsonObject.getStr("chatid");
        }
        return null;
    }

    public static void appchatupdate() {

    }

    /**
     * 应用推送消息
     */
    private static final String appchatsendUrl = "https://qyapi.weixin.qq.com/cgi-bin/appchat/send?access_token=%s";

    /**
     * 文本消息
     * chatid	是	群聊id
     * msgtype	是	消息类型，此时固定为：text
     * content	是	消息内容，最长不超过2048个字节
     * safe	否	表示是否是保密消息，0表示否，1表示是，默认0
     */
    public static JSONObject appchatsend() {
        JSONObject paramMap = new JSONObject();
        paramMap.put("chatid", chatid);
        paramMap.put("msgtype", "text");
        paramMap.put("text", new JSONObject() {{
            put("content", "測試消息");
        }});
        paramMap.put("safe", 0);
        String res = HttpUtil.post(String.format(appchatsendUrl, token), paramMap.toString());
        return JSONUtil.parseObj(res);
    }

    /**
     * 获取群聊会话
     */
    private static final String appchatgetUrl = "https://qyapi.weixin.qq.com/cgi-bin/appchat/get?access_token=%s&chatid=%s";

    public static JSONObject appchatget() {
        String res = HttpUtil.get(String.format(appchatgetUrl, token, chatid));
        return JSONUtil.parseObj(res);
    }

    public static void main(String[] args) {
//        List<String> useridlist = new ArrayList<>();
//        useridlist.add("18538158972");
//        useridlist.add("BuFan");
//        System.out.println(appchatCreate("18538158972", useridlist));
//        System.out.println(appchatsend());
        System.out.println(appchatget());
    }
}
