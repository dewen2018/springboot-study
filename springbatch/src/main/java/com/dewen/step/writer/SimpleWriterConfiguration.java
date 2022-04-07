package com.dewen.step.writer;

import com.dewen.model.vo.People;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Slf4j
@Configuration
public class SimpleWriterConfiguration {

    @Bean(name = "peopleBatchItemWriter")
    @StepScope
    public MyBatisBatchItemWriter<People> writePeople(@Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        MyBatisBatchItemWriter<People> peopleBatchItemWriter = new MyBatisBatchItemWriter<People>();
        peopleBatchItemWriter.setSqlSessionFactory(sqlSessionFactory);
        peopleBatchItemWriter.setStatementId("insertPeople");
        return peopleBatchItemWriter;
    }
}
