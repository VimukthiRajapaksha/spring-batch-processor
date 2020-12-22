package com.demo.batchprocessor.configurations;


import com.demo.batchprocessor.bean.CustomerInfoBean;
import com.demo.batchprocessor.listener.JobCompletionNotificationListener;
import com.demo.batchprocessor.processor.CustomerInfoProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
public class BeanConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<CustomerInfoBean> reader() {
        return new FlatFileItemReaderBuilder<CustomerInfoBean>()
                .name("CustomerInfoItemReader")
                .resource(new ClassPathResource("a_30000.csv"))
                .delimited()
                .names(new String[]{"cardNumber", "mobileNumber", "email", "expDate", "status"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<CustomerInfoBean>() {{
                    setTargetType(CustomerInfoBean.class);
                }})
                .build();
    }

    @Bean
    public CustomerInfoProcessor processor() {
        return new CustomerInfoProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<CustomerInfoBean> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<CustomerInfoBean>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO customerInfo (cardNumber, mobileNumber, email, expDate, status) VALUES (:cardNumber, :mobileNumber, :email, :expDate, :status)")
                .dataSource(dataSource)
                .build();
    }

    @Bean(name = "importUserJob")
    public Job importUserJob(JobCompletionNotificationListener listener, @Qualifier("step1") Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean(name = "step1")
    public Step step1(JdbcBatchItemWriter<CustomerInfoBean> writer) {
        return stepBuilderFactory.get("step1")
                .<CustomerInfoBean, CustomerInfoBean> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}
