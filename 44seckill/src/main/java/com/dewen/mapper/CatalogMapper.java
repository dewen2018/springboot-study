package com.dewen.mapper;

import com.dewen.entity.Catalog;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CatalogMapper {

    @Insert("insert into catalog (name, total, sold) values (#{name}, #{total}, #{sold}) ")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Long insertCatalog(Catalog catalog);

    @Update("update catalog set name=#{name}, total=#{total}, sold=#{sold} where id=#{id}")
    Long updateCatalog(Catalog catalog);

    @Select("select * from catalog where id=#{id}")
    Catalog selectCatalog(@Param("id") Long id);
}
