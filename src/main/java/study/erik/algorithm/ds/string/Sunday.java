package study.erik.algorithm.ds.string;

import org.junit.Assert;
import org.junit.Test;
import sun.security.provider.Sun;

/**
 * @author erik.wang
 * @date 2020-05-10 20:14
 */
public class Sunday {


    private String target;
    private String pattern;

    /**
     * 参见：https://www.cnblogs.com/RioTian/p/12829532.html
     */
    public Sunday() {
    }

    public static class Builder {
        private Sunday sunday = new Sunday();

        public Builder setTarget(String target) {
            sunday.setTarget(target.toLowerCase());
            return this;
        }

        public Builder setPattern(String pattern) {
            sunday.setPattern(pattern.toLowerCase());
            return this;
        }

        public Sunday build() {
            return this.sunday;
        }
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int search() {

        int[] lastIndex = new int[26];
        int pLen = pattern.length();
        int tLen = target.length();

        for (int i = 0; i < lastIndex.length; i++) {
            //-1表示 当主串的焦点字符c没有在pattern中出现时，直接跳过patternLength + 1 的长度
            lastIndex[i] = -1;
        }

        for (int i = 0; i < pLen; i++) {
            //记录字符c在pattern最后出现的位置
            lastIndex[pattern.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i <= tLen - pLen; ) {
            int j = 0;
            while (j < pLen && target.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }
            if (j == pLen) {
                return i;
            }
            char c = target.charAt(i + pLen);
            int index = lastIndex[c - 'a'];
            i += (pLen - index);
        }
        return -1;

    }

    @Test
    public void test_() {
        Assert.assertEquals(13, new Sunday.Builder()
                .setTarget("HEREISASIMPLEEXAMPLE")
                .setPattern("EXAMPLE")
                .build()
                .search());
    }
}
