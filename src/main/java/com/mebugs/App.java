package com.mebugs;

import lombok.extern.slf4j.Slf4j;
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
