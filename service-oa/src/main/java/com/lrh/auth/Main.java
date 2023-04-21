package com.lrh.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.lrh")       //不只是service-oa的com.lrh包下的类被扫描
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}