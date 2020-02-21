package com.mouse.core.task;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author ; lidongdong
 * @Description
 * @Date 2020-02-05
 */
public enum AsyncTaskExecutorEnum {

    INSTANCE;

    /**************************************************************************************************************
     * 常见的几种线程技术
     **************************************************************************************************************
     * Java通过Executors提供四种线程池，分别为：
     * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。 newSingleThreadExecutor
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     *
     * 1、public static ExecutorService newFixedThreadPool(int nThreads) {
     * return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()); }
     *
     * 2、 public static ExecutorService newSingleThreadExecutor() {
     * return new FinalizableDelegatedExecutorService (new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())); }
     *
     * 3、public static ExecutorService newCachedThreadPool() {
     * return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>()); }
     ****************************************************************************************************************/

    /**
     * 手动初始化线程池
     * corePoolSize - 池中所保存的线程数，包括空闲线程。
     * maximumPoolSize - 池中允许的最大线程数。
     * keepAliveTime - 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
     * unit - keepAliveTime 参数的时间单位。
     * workQueue - 执行前用于保持任务的队列。此队列仅由保持 execute 方法提交的 Runnable 任务。
     * handler - 由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序。
     * <p>
     * LinkedBlockingQueue 默认大小 Integer.MAX_VALUE,所以如果不指定大小 最大线程数量设置无效, 线程过多可能造成内存溢出
     * <p>
     * handler ： 线程池对拒绝任务的处理策略。在 ThreadPoolExecutor 里面定义了 4 种 handler 策略，如下
     * 1. CallerRunsPolicy ：这个策略重试添加当前的任务，他会自动重复调用 execute() 方法，直到成功。
     * 2. AbortPolicy ：对拒绝任务抛弃处理，并且抛出异常。会中断调用者的处理过程
     * 3. DiscardPolicy ：对拒绝任务直接无声抛弃，丢弃当前将要加入队列的任务本身,没有异常信息。
     * 4. DiscardOldestPolicy ：对拒绝任务不抛弃，而是抛弃队列里面等待最久的一个线程，然后把当前拒绝任务加到队列。
     */
    AsyncTaskExecutorEnum() {
        ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setNameFormat("credit-threadPool-%d").build();
        threadPoolExecutor = new ThreadPoolExecutor(SIZE_CORE_POOL, SIZE_MAX_POOL, 10L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100), nameThreadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    private ThreadPoolExecutor threadPoolExecutor;

    public ExecutorService getExecutors() {
        return threadPoolExecutor;
    }

    /**
     * 核心线程数,线程池维护线程的最少数量
     */
    private static final int SIZE_CORE_POOL = 10;

    /**
     * 线程池维护线程的最大数量
     */
    private static final int SIZE_MAX_POOL = 100;

    /**
     * 执行线程 无返回值
     *
     * @param runnable
     */
    public static void execute(Runnable runnable) {
        INSTANCE.getExecutors().execute(runnable);
    }

    /**
     * 有返回值
     *
     * @param runnable
     */
    public static Future<?> submit(Runnable runnable) {
        return INSTANCE.getExecutors().submit(runnable);
    }

    /**
     * 有返回值
     *
     * @param runnable
     */
    public static <T> Future<T> submit(Runnable runnable, T result) {
        return INSTANCE.getExecutors().submit(runnable, result);
    }

    /**
     * 有返回值
     *
     * @param callable
     */
    public static <T> Future<T> submit(Callable<T> callable) {
        return INSTANCE.getExecutors().submit(callable);
    }

    /**
     * 将线程池初始化，核心线程数量
     */
    public void perpare() {
        if (threadPoolExecutor.isShutdown() && !threadPoolExecutor.prestartCoreThread()) {
            @SuppressWarnings("unused")
            int startThread = threadPoolExecutor.prestartAllCoreThreads();
        }
    }

    /**
     * 向线程池中添加任务方法
     */
    public void addExecuteTask(Runnable task) {
        if (task != null) {
            threadPoolExecutor.execute(task);
        }
    }

    /**
     * 判断是否是最后一个任务
     */
    protected boolean isTaskEnd() {
        if (threadPoolExecutor.getActiveCount() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取缓存大小
     */
    public int getQueue() {
        return threadPoolExecutor.getQueue().size();
    }

    /**
     * 获取线程池中的线程数目
     */
    public int getPoolSize() {
        return threadPoolExecutor.getPoolSize();
    }

    /**
     * 获取已完成的任务数
     */
    public long getCompletedTaskCount() {
        return threadPoolExecutor.getCompletedTaskCount();
    }

    /**
     * 关闭线程池，不在接受新的任务，会把已接受的任务执行玩
     */
    public void shutdown() {
        threadPoolExecutor.shutdown();
    }

}
