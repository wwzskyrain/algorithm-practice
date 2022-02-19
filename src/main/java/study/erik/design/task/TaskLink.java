/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.design.task;

import study.erik.design.task.work.ListenPrd;
import study.erik.design.task.work.WorkTask;
import study.erik.design.task.work.WriteDetailAnalysis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : TaskLink.java, v 0.1 2022年02月16日 7:47 下午 yueyi Exp $
 */
public class TaskLink<T extends Task> {

    private final List<T>  tasks;
    private final Class<T> taskClass;

    public String getTaskType() {
        return taskClass.getCanonicalName();
    }

    public TaskLink(Class<T> taskClass) {
        this.taskClass = taskClass;
        this.tasks = new ArrayList<>();
    }

    public void addTask(T task) {
        tasks.add(task);
    }

    public static void main(String[] args) {

        TaskLink<WorkTask> workTaskTaskLink = new TaskLink<>(WorkTask.class);
        workTaskTaskLink.addTask(new WriteDetailAnalysis());
        workTaskTaskLink.addTask(new ListenPrd());

    }
}