package com.dingding.permissions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.stream.Stream;

@SpringBootApplication
public class PermissionsApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(PermissionsApplication.class, args);
        String[] beanNames = ctx.getBeanDefinitionNames();
        Stream.of(beanNames).forEach(System.out::println);
    }

}
