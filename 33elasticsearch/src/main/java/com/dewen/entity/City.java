package com.dewen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 城市
 */
@Data
@Document(indexName = "city")
@ApiModel(value = "city",description = "城市")
public class City implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 城市编号
     */
    @Id
    private String id;

    /**
     * 省份编号
     */
    //自动检测类型
    @Field(type = FieldType.Auto)
    private Long provinceid;

    /**
     * 城市名称
     */
    //手动设置为keyword  但同时也就不能分词
    @ApiModelProperty(value = "城市名称", required = true, example = "珠海")
    @Field(type = FieldType.Keyword)
    private String cityname;

    /**
     * 描述
     */
    //设置为text  可以分词
    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_max_word")
    private String description;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createtime;

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", provinceid=" + provinceid +
                ", cityname='" + cityname + '\'' +
                ", description='" + description + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}