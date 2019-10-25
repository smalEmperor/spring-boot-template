package com.easicare.device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 项目启动类
 * @author df
 * @date 2019/8/6
 */
//@EnableApi2Doc
@SpringBootApplication
@EnableTransactionManagement    //开启事务
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
