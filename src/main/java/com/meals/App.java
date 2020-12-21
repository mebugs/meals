package com.meals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * SpringBoot启动入口类
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class App
{

    public static void main( String[] args )
    {
        System.out.println( "--- Start ---" );
        SpringApplication.run(App.class, args);
    }
}
