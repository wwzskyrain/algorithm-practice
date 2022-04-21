/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author yueyi
 * @version : EmployeeImportance.java, v 0.1 2022年04月21日 7:19 下午 yueyi Exp $
 */
public class EmployeeImportance {

    class Employee {
        public int           id;
        public int           importance;
        public List<Integer> subordinates;
    }

    @LetCodeCommit(title = "690. Employee Importance",
            diff = "m",
            selfRemark = "这个是变相的树题，实在简单")
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        Employee employee = map.get(id);
        int total = 0;
        Deque<Employee> q = new LinkedList<>();
        q.offer(employee);
        while (!q.isEmpty()) {
            Employee poll = q.poll();
            total += poll.importance;
            for (Integer subId : poll.subordinates) {
                q.offer(map.get(subId));
            }
        }
        return total;
    }

}