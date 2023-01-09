package com.dewen.graphql.student.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * @author dewen
 * @date 2023/1/7 16:39
 */
@Data
public class StudentDTO {
    // @Min(value = 1, message = "id错误")
    // private int id;
    @Length(min = 2, max = 10, message = "名称过长")
    private String name;
    @Range(min = 1, max = 100, message = "年龄不正确")
    private Integer age;
}
