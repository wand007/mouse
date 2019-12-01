package com.mouse.admin.config;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;


/**
 * @author ; lidongdong
 * @Description 线程池配置类
 * @Date 2019-09-19
 */
public class ThreadPoolConfig {

    private Integer corePoolSize;

    private Integer maxPoolSize;

    private Integer queueCapacity;

    private Integer keepAliveSeconds;

    private String threadPoolName;

    public ThreadPoolConfig(Integer corePoolSize, Integer maxPoolSize, Integer queueCapacity,
                            Integer keepAliveSeconds, String threadPoolName) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.queueCapacity = queueCapacity;
        this.keepAliveSeconds = keepAliveSeconds;
        this.threadPoolName = threadPoolName;
    }

    public ThreadPoolTaskExecutor getThreadPool() {
        ThreadPoolTaskExecutor asyncTaskThreadPool = new ThreadPoolTaskExecutor();
        asyncTaskThreadPool.setCorePoolSize(corePoolSize);
        asyncTaskThreadPool.setMaxPoolSize(maxPoolSize);
        asyncTaskThreadPool.setQueueCapacity(queueCapacity);
        asyncTaskThreadPool.setKeepAliveSeconds(keepAliveSeconds);
        asyncTaskThreadPool.setThreadFactory(new ThreadFactory() {
            private final AtomicLong index = new AtomicLong(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, threadPoolName + index.getAndIncrement());
            }
        });
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        asyncTaskThreadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        asyncTaskThreadPool.initialize();
        return asyncTaskThreadPool;
    }
}
