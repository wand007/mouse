package com.mouse.api.commons;

import com.mouse.core.task.AsyncTaskExecutorEnum;
import com.mouse.core.task.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.DelayQueue;

/**
 * @author ; lidongdong
 * @Description 延时任务
 * @Date 2020-02-05
 */
@Slf4j
@Component
public class TaskComm {

    private DelayQueue<Task> delayQueue = new DelayQueue<Task>();


    @PostConstruct
    private void init() {

        AsyncTaskExecutorEnum.INSTANCE.getExecutors().execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Task task = delayQueue.take();
                        task.run();
                    } catch (Exception e) {
                        log.error("延时任务队列添加异常", e);
                    }
                }
            }
        });
    }

    public void addTask(Task task) {
        if (delayQueue.contains(task)) {
            return;
        }
        delayQueue.add(task);
    }

    public void removeTask(Task task) {
        delayQueue.remove(task);
    }
}
