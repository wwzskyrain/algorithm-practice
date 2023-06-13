/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.string;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yueyi
 * @version : CheckIfStringIsTransformableWithSubstringSortOperations.java, v 0.1 2023年05月29日 11:28 yueyi Exp $
 */
public class CheckIfStringIsTransformableWithSubstringSortOperations {

    @LetCodeCommit(title = "1585. Check If String Is Transformable With Substring Sort Operations",
            diff = "h",
            selfRemark = "这是一个需要巧妙思考的题目。"
                    + "首先要理解题意，其中的交互，就是可以把'd'前移到任何一个比他小的字符的后面。当然，根据target也可以不用移动。"
                    + "把s字符串解构成桶排序的形式。"
                    + "然后看着target字符，从左到右，把s中的字符一个一个的测试可否移动。")
    public boolean isTransformable(String s, String t) {
        Queue<Integer>[] queues = new Queue[10];
        for (int i = 0; i < queues.length; i++) {
            queues[i] = new LinkedList<>();
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int d = chars[i] - '0';
            queues[d].add(i);
        }
        chars = t.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int d = chars[i] - '0';
            if (queues[d].isEmpty()) {
                return false;
            }
            int targetCharIndex = queues[d].peek();
            for (int j = 0; j < d; j++) {
                if (!queues[j].isEmpty() && queues[j].peek() < targetCharIndex) {
                    return false;
                }
            }
            queues[d].poll();
        }
        return true;
    }

}