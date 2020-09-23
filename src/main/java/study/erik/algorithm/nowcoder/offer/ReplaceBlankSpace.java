package study.erik.algorithm.nowcoder.offer;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-09-12 10:49
 */
public class ReplaceBlankSpace {


    public String replaceSpace(StringBuffer str) {
        StringBuilder sb = new StringBuilder();

        String input = str.toString();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    @Test
    public void test_solution() {
        StringBuffer sb = new StringBuffer("We Are Happy");
        String expect = "We%20Are%20Happy";
        Assert.assertEquals(expect, replaceSpace(sb));
    }

}
