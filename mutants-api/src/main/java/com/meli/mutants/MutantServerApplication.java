package com.meli.mutants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
}
