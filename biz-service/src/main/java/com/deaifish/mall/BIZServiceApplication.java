package com.deaifish.mall;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties    //开启jasypt加密
public class BIZServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BIZServiceApplication.class, args);
    }
}