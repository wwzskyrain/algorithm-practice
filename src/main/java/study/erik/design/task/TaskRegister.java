/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.design.task;

import study.erik.design.task.work.ListenPrd;
import study.erik.design.task.work.WorkTask;
import study.erik.design.task.work.WriteDetailAnalysis;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : TaskRegister.java, v 0.1 2022年02月16日 8:06 下午 yueyi Exp $
 * 1.注册容器不应该理解其内部元素的具体类型，最多只需要一个泛型约束即可
 * 2.容器中的元素使用原则：谁取谁就得会用。或者再说明白一些：
 * 3.用指定的类型取，取出来的一定是具体类型的obj了，这样就可以编译其用obj的方法了。
 * 4.在返回get调用者之前，要用Class<T>来做cast，不要用(T)obj，虽然class.cast(obj)底层也是(T)obj
 */
public class TaskRegister {

    private final Map<String, Task> map = new HashMap<>();

    public void register(Task task) {
        String taskType = task.getClass().getCanonicalName();
        map.put(taskType, task);
    }

    public <T> T getTask(Class<T> taskClass) {
        Task task = map.get(taskClass.getCanonicalName());
        return taskClass.isInstance(task) ? taskClass.cast(task) : null;
    }

    public static void main(String[] args) {

        TaskRegister taskRegister = new TaskRegister();
        taskRegister.register(new ListenPrd());
        taskRegister.register(new WriteDetailAnalysis());

        WorkTask listenPrd = taskRegister.getTask(ListenPrd.class);
        listenPrd.doWork();

        WorkTask workTask = taskRegister.getTask(WorkTask.class);
        workTask.doWork();

    }

}