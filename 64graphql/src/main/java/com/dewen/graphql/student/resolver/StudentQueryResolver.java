package com.dewen.graphql.student.resolver;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dewen.commons.PageResult;
import com.dewen.graphql.student.pojo.Student;
import com.dewen.mapper.StudentMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class StudentQueryResolver implements GraphQLQueryResolver {
    @Resource
    private StudentMapper studentMapper;
    // 异步 Resolver
    // 直接使用 CompletableFuture 作为 Resolver 的返回对象即可。
    private final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @PreDestroy
    public void destroy() {
        executor.shutdown();
    }

    public List<Student> findAll() {
        return this.studentMapper.selectList(new QueryWrapper<Student>());
    }

    public Student findById(Integer id) {
        return this.studentMapper.selectById(id);
    }

    public List<Student> findByName(String name) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().like(StrUtil.isNotBlank(name), Student::getName, name);
        return this.studentMapper.selectList(queryWrapper);
    }

    public PageResult<Student> findAllByPage(int pageNo, int pageSize) {
        IPage<Student> page = this.studentMapper.selectPage(new Page<Student>(pageNo - 1, pageSize), new QueryWrapper<>());
        PageResult<Student> pageResult = new PageResult<>();
        pageResult.setItems(page.getRecords());
        pageResult.setPageNo(pageNo);
        pageResult.setPageSize((int) page.getPages());
        pageResult.setTotalCount((int) page.getTotal());
        return pageResult;
    }


    // public Connection<Student> students(int first, String after) {
    //     String afterToUsed = StringUtils.defaultIfEmpty(after, "0");
    //
    //     Integer minId = this.studentMapper.findMinId();
    //     Integer maxId = this.studentMapper.findMaxId();
    //
    //     // 从 after 游标开始,取 first 个数据
    //     // 这里故意取 first + 1 个数,用来判断是否还有下一页数据
    //     List<Student> students = this.studentMapper.findByIdGreaterThan(Integer.valueOf(afterToUsed), new Page<>(0, first + 1));
    //
    //     List<Edge<Student>> edges = students.stream()
    //             .limit(first)
    //             .map(student -> new DefaultEdge<>(student, new DefaultConnectionCursor(String.valueOf(student.getId()))))
    //             .collect(Collectors.toList());
    //
    //     PageInfo pageInfo =
    //             new DefaultPageInfo(
    //                     new DefaultConnectionCursor(String.valueOf(minId)),
    //                     new DefaultConnectionCursor(String.valueOf(maxId)),
    //                     Integer.parseInt(afterToUsed) > minId,
    //                     students.size() > first);
    //
    //     return new DefaultConnection<>(edges, pageInfo);
    // }

    // supplyAsync
    public CompletableFuture<Collection<Student>> getStudents() {
        log.info("start getStudents...");
        CompletableFuture<Collection<Student>> future = CompletableFuture.supplyAsync(() -> {
            log.info("invoke getStudents...");
            sleep();
            Student student = new Student(8, "Dewen", 18);
            return Collections.singletonList(student);
        }, executor);
        log.info("end getStudents...");
        return future;
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}