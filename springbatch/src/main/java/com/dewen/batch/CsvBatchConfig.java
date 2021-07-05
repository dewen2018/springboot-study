package com.dewen.batch;

import com.dewen.entity.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.batch.support.DatabaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


/**
 * @author wxw
 * @version 2019/1/18
 */
@Configuration
@EnableBatchProcessing
public class CsvBatchConfig {
    @Autowired
    DataSource dataSource;
    @Autowired
    PlatformTransactionManager platformTransactionManager;
    @Autowired
    private JobRepository jobRepository;

    @Bean
    public ItemReader<Person> reader() throws Exception {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        reader.setName("readCsv");
        reader.setResource(new ClassPathResource("person.csv"));
        DefaultLineMapper<Person> defaultLineMapper = new DefaultLineMapper<>();
        reader.setLineMapper(defaultLineMapper);

        DelimitedLineTokenizer tokenizer;
        defaultLineMapper.setLineTokenizer(tokenizer = new DelimitedLineTokenizer());
        tokenizer.setNames(new String[]{"name", "age", "nation", "address"});

        BeanWrapperFieldSetMapper<Person> setMapper;
        defaultLineMapper.setFieldSetMapper((setMapper = new BeanWrapperFieldSetMapper<>()));
        setMapper.setTargetType(Person.class);

        return reader;
    }

    @Bean
    public ItemProcessor<Person, Person> processor() {
        CsvItemProcessor processor = new CsvItemProcessor();
        processor.setValidator(csvBeanValidator());
        return processor;
    }

    @Bean
    public ItemWriter<Person> writer() {
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
        String sql = "insert into person (name,age,nation,address) values (:name,:age,:nation,:address)";
        writer.setSql(sql);
        writer.setDataSource(dataSource);
        return writer;
    }

//    @Bean
//    public JobRepository jobRepository() throws Exception {
//        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
//        jobRepositoryFactoryBean.setDataSource(dataSource);
//        jobRepositoryFactoryBean.setTransactionManager(platformTransactionManager);
//        jobRepositoryFactoryBean.setDatabaseType(DatabaseType.MYSQL.name());
//        return jobRepositoryFactoryBean.getObject();
//    }

    @Bean
    public SimpleJobLauncher jobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;

    }

    @Bean
    public Job importJob(JobBuilderFactory jobs, Step step1) {
        return jobs.get("importJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)

                .end()
                .listener(csvJobListner())
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader, ItemWriter<Person> writer, ItemProcessor<Person, Person> processor) {
        return stepBuilderFactory.get("step1")
                .<Person, Person>chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public CsvJobListner csvJobListner() {
        return new CsvJobListner();
    }

    @Bean
    public Validator<Person> csvBeanValidator() {
        return new CsvBeanValidator<Person>();
    }
}
