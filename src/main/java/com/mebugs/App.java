package com.mebugs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot启动入口类
 */
@SpringBootApplication
public class App
{

    public static void main( String[] args )
    {
        System.out.println( "--- Start ---" );
        SpringApplication.run(App.class, args);
    }
}
