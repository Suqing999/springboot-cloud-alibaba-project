package com.suki.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
=======
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
>>>>>>> 4ffed4f (first)
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
<<<<<<< HEAD
=======
@EnableDiscoveryClient
>>>>>>> 4ffed4f (first)
@ComponentScan("com.suki")
public class MypracticeOssApplication {
    public static void main(String[] args) {
        SpringApplication.run(MypracticeOssApplication.class, args);
    }
}
