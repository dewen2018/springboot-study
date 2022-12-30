package com.dewen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dewen.entity.GroovyScript;
import org.apache.ibatis.annotations.Select;

public interface GroovyScriptMapper extends BaseMapper<GroovyScript> {

    @Select({"select script_content from groovy_script where id = #{id}"})
        // @Result(column = "script_content", property = "scriptContent")
    GroovyScript getOne(Long id);
}
