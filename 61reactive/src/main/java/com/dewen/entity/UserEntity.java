package com.dewen.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@Table("user")
public class UserEntity implements Persistable<Long>, Serializable {
    @Id
    private Long id;
    @Column("name")
    private String name;
    private String phone;
    private String mail;

    @Override
    public boolean isNew() {
        // return StrUtil.isBlank(this.id);
        return null == this.id;
    }
}
