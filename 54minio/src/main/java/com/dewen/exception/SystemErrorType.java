package com.dewen.exception;

import lombok.Getter;

@Getter
public enum SystemErrorType implements ErrorType {

    SYSTEM_ERROR(-1, "系统异常"),
    SYSTEM_BUSY(100001, "系统繁忙,请稍候再试"),

    GATEWAY_NOT_FOUND_SERVICE(100404, "服务未找到"),
    GATEWAY_ERROR(100500, "网关异常"),
    GATEWAY_CONNECT_TIME_OUT(100002, "网关超时"),

    ARGUMENT_NOT_VALID(101000, "请求参数校验不通过"),
    INVALID_TOKEN(101001, "无效的token"),
    GET_TOKEN_ERROR(101002,"获取token失败"),
    TOKEN_NOT_FOUND(101003, "token不存在"),
    UPLOAD_FILE_SIZE_LIMIT(101010, "上传文件大小超过限制"),
    UPLOAD_FILE_ERROR(101011, "上传文件异常"),

    EXCEL_ERROR(101021, "excel表格操作异常"),

    USER_ACCOUNT_OR_PWD_ERROR(101100,"用户账号或密码错误"),
    USER_OLDPWD_ERROR(101101,"用户原密码输入错误"),

    DUPLICATE_PRIMARY_KEY(103000,"唯一键冲突");



    /**
     * 错误类型码
     */
    private Integer code;
    /**
     * 错误类型描述信息
     */
    private String mesg;

    SystemErrorType(Integer code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }
}
