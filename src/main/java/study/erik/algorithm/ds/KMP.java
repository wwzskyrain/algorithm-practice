package study.erik.algorithm.ds;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author erik.wang
 * @date 2020-05-07 17:42
 */
public class KMP {

    public static int[] getNext(String ps) {

        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        // 本来以为next[0]没有意义的，其实是有意义的，而且赋值为一个小于0的
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            //k==-1 表示p[j]和p[0]也不相等，那么这时next[j+1]=0，因为此时
            //p[0...j]的最大相等前后缀长度为0，也即在search时，如果p[j+1]不匹配
            //那就只能让s[i]与p[0]匹对试下了。
            if (k == -1 || p[j] == p[k]) {
                next[++j] = ++k;
            } else {
                // 这里是很有意思的：这里的k就是外面的j
                k = next[k];
            }
        }
        return next;
    }

    /**
     * 在target中查找第一个子串'pattern'的位置，注意这里是精确查找
     *
     * @param target
     * @param pattern -1表示没有找到
     * @return
     */
    public static int search(String target, String pattern) {

        int[] next = getNext(pattern);

        int i = 0, j = 0;
        while (i < target.length() && j < pattern.length()) {
            //j == -1 表示t[i]和p[0]也不配，所以就算了，i就下一个吧，j也++变成0，即准备下一个i和p[0]再匹配
            if (j == -1 || target.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == pattern.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    @Test
    public void test_() {
        Assert.assertEquals(-1, search("SSSSSSSSSSSSSA", "SSSSB"));
        Assert.assertEquals(-1, search("SSSSSSSSSSSSSA", "SSSSB"));

    }

}
