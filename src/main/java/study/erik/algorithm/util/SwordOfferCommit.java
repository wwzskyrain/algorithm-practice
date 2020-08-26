package study.erik.algorithm.util;

/**
 * @author erik.wang
 * @date 2020-08-26 09:55
 */
public @interface SwordOfferCommit {

    String title();
    String review();
    QuestionType[] type() default {};





}
