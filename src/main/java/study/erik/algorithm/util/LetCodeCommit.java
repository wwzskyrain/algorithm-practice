package study.erik.algorithm.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;

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

    int space() default 0;

    String diff() default "e";

    String[] related() default "";

    String selfRemark() default "";

    String extend() default "";

    Type[] types() default {};

    enum Type {
        DP, Double_Point, Slide_Window, State_Mechine, Detail, Matrix, Tree;
    }
}
