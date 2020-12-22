package com.demo.batchprocessor.listener;

import com.demo.batchprocessor.bean.CustomerInfoBean;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("!!! JOB FINISHED! Time to verify the results");

//            jdbcTemplate.query("SELECT cardNumber, mobileNumber, email FROM customerInfo",
//                    (rs, row) -> new CustomerInfoBean(
//                            rs.getString(1),
//                            rs.getString(2),
//                            rs.getString(2))
//            ).forEach(customer -> System.out.println("Found <" + customer + "> in the database."));
            int count = jdbcTemplate.queryForObject("SELECT COUNT(*) from customerInfo", Integer.class);
            System.out.println("**** COUNT **** : " + count);

            jdbcTemplate.query("SELECT cardNumber, mobileNumber, email FROM customerInfo ORDER BY customer_id DESC LIMIT 1",
                    (rs, row) -> new CustomerInfoBean(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(2))
            ).forEach(customer -> System.out.println("LAST CUSTOMER <" + customer + "> in the database."));

            jdbcTemplate.query("SELECT cardNumber, mobileNumber, email FROM customerInfo ORDER BY customer_id ASC LIMIT 1",
                    (rs, row) -> new CustomerInfoBean(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(2))
            ).forEach(customer -> System.out.println("FIRST CUSTOMER <" + customer + "> in the database."));
        }
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        super.beforeJob(jobExecution);
    }
}
