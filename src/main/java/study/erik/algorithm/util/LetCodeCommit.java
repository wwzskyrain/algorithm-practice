package study.erik.algorithm.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author erik.wang
 * @date 2020-08-04 09:09
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.METHOD})
public @interface LetCodeCommit {

    String title() default "";

    int no() default 0;

    int time() default 0;

    int timeMillseconde() default 100000;

    int space() default 0;


    String diff() default "e";

    /**
     * 相关题目
     *
     * @return
     */
    String[] related() default "";

    /**
     * 自我评价
     *
     * @return
     */
    String selfRemark() default "";

    /**
     * 扩展
     *
     * @return
     */
    String extend() default "";

    /**
     * 题目类型
     *
     * @return
     */
    Type[] types() default {};

    enum Type {
        /**
         * 动态规划
         */
        DP,
        /**
         * 双指针
         */
        Double_Point,
        /**
         * 滑动窗口
         */
        Slide_Window,
        /**
         * 有限状态机器
         */
        State_Mechine,
        /**
         * 细节题
         */
        Detail,
        /**
         * 矩阵
         */
        Matrix,
        /**
         * 树
         */
        Tree,
        /**
         * 贪心
         */
        Greedy,
        /**
         * 二分查找
         */
        BinarySearch,
        /**
         * 数组
         */
        Array,
        /**
         * 链表
         */
        Link_List;
    }
}
