package com.meli.mutants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Class that can be used to bootstrap and launch a Spring application from a Java main method
 *
 * @author andres montoya - andresmontoyat@gmail.com
 * @version 1.0
 */
@SpringBootApplication
public class MutantServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MutantServerApplication.class, args);
    }

    @Bean
    public AsyncTaskExecutor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(6);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(40);
        return executor;
    }
}
