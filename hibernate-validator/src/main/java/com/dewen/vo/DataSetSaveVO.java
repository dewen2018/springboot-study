package com.dewen.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class DataSetSaveVO {
    //唯一标识符为空
    @NotBlank(message = "user uuid is empty")
    //用户名称只能是字母和数字
    //@Pattern(regexp = "^[a-z0-9]+$", message = "user names can only be alphabetic and numeric")
    @Length(min = 3, max = 48, message = "user uuid length nust between 3~48 byte")
    private String userUuid;

    //数据集名称只能是字母和数字
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "data set names can only be letters and Numbers")
    //文件名称过长
    @Length(max = 48, message = "file name too long")
    //文件名称为空
    @NotBlank(message = " file name is empty")
    private String name;

    //数据集描述最多为256字节
    @Length(max = 256, message = "data set description length over 256 byte")
    //数据集描述为空
    @NotBlank(message = " data set description is null")
    private String description;

    @Email(message = "邮箱格式不正确")
    private String email;

    @Pattern(regexp = "^((https|http)?:\\/\\/)[^\\s]+$", message = "请填写合法的URL")
    private String url;

    @NotNull(message = " 字段不能为空")
    private String sex;

    @Size(min = 3, max = 100, message = "Collection size must between 3~100")
    private List<String> names;
}