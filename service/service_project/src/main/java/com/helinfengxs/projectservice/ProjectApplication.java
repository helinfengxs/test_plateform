package com.helinfengxs.projectservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.helinfengxs"})
@EnableTransactionManagement
public class ProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class,args);
    }
}
