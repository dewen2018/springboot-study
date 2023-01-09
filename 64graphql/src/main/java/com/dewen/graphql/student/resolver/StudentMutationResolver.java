package com.dewen.graphql.student.resolver;

import com.dewen.commons.Result;
import com.dewen.graphql.student.pojo.Student;
import com.dewen.graphql.student.pojo.StudentDTO;
import com.dewen.mapper.StudentMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;

@Slf4j
@Component
// 校验入参
@Validated
public class StudentMutationResolver implements GraphQLMutationResolver {
    @Resource
    private StudentMapper studentMapper;

    @Transactional
    public Result addStudent(@Valid StudentDTO input) {
        Student student = new Student(input.getName(), input.getAge());
        this.studentMapper.insert(student);
        return new Result(200, "success");
    }
}