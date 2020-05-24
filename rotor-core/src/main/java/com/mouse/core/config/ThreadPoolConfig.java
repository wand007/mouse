package com.mouse.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;


/**
 * @author ; lidongdong
 * @Description 自定义线程池，以及异常处理
 * @Date 2019-09-19
 */
@Slf4j
public class ThreadPoolConfig implements AsyncConfigurer {

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

    /**
     *
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new ThreadPoolConfig.SpringAsyncExceptionHandler();
    }

    class SpringAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
            log.error("Exception occurs in async method", throwable.getMessage());
        }
    }
}
