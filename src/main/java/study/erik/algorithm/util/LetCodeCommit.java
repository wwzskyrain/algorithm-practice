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
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.CONSTRUCTOR})
public @interface LetCodeCommit {

    String title() default "";

    int no() default 0;

    int time() default 0;

    int timeMillisecond() default 100000;

    int space() default 0;

    String postLink() default "";

    String diff() default "e";

    String tag() default "";

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

    FavoriteLevel favorite() default FavoriteLevel.ONE_STAR;

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
    QuestionType[] types() default {};

}
