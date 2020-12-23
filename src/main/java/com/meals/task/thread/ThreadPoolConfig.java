package com.meals.task.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author mebugs.com/米虫先生
 * @since 2020-09-10
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean("threadPool")
    public Executor ThreadPool(){
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.initialize();
        // 设置核心线程数
        threadPool.setCorePoolSize(5);
        // 设置最大线程数
        threadPool.setMaxPoolSize(10);
        // 设置队列容量
        threadPool.setQueueCapacity(20);
        // 设置线程活跃时间（秒）
        threadPool.setKeepAliveSeconds(60);
        // 设置默认线程名称
        threadPool.setThreadNamePrefix("threadPool");
        // 设置拒绝策略  直接调用execute来执行当前任务
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        // 设置等待关闭时长
        threadPool.setAwaitTerminationSeconds(60);
        return threadPool;
    }
}
