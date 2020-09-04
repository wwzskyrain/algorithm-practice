package study.erik.algorithm.leetcode.string;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author erik.wang
 * @date 2020-09-01 13:40
 */
public class DecodeString {

    @LetCodeCommit(title = "394. Decode String", diff = "m",
            selfRemark = "这个题目我以为很简单，但是从昨天下午到尽头早上，我做了蛮久的。" +
                    "题目很干脆，也挺有意思的。" +
                    "解法：括弧匹配+递归调用。整体比较好理解。" +
                    "当然也可以把递归用栈转化成迭代，接下来就要这样解答",
            related = {"726. Number of Atoms"})
    public String decodeString(String s) {
        return decode(s);
    }

    public String decode(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (isValidChar(c)) {
                sb.append(c);
                i++;
                continue;
            }

            if (isNumberChar(c)) {
                int count = 0;
                while (isNumberChar(c)) {
                    count = count * 10 + c - '0';
                    i++;
                    c = s.charAt(i);
                }
                Deque<Integer> stack = new LinkedList<>();
                while (i < s.length()) {
                    c = s.charAt(i);
                    if (c == '[') {
                        stack.push(i);
                    } else if (c == ']') {
                        Integer start = stack.pop();
                        if (stack.isEmpty()) {
                            String word = decode(s.substring(start + 1, i));
                            while (count > 0) {
                                sb.append(word);
                                count--;
                            }
                            i++;
                            break;
                        }
                    }
                    i++;
                }
            }
        }
        return sb.toString();
    }

    private boolean isNumberChar(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isValidChar(char c) {
        return !isNumberChar(c) && (c != '[') && (c != ']');
    }


    @Test
    public void test_solution_1() {
        String s = "3[a]2[bc]";
        Assert.assertEquals("aaabcbc", decodeString(s));
    }

    @Test
    public void test_solution_2() {
        String s = "3[a2[c]]";
        Assert.assertEquals("accaccacc", decodeString(s));
    }

    @Test
    public void test_solution_3() {
        String s = "2[abc]3[cd]ef";
        Assert.assertEquals("abcabccdcdcdef", decodeString(s));
    }

    @Test
    public void test_solution_4() {
        String s = "abc3[cd]xyz";
        Assert.assertEquals("abccdcdcdxyz", decodeString(s));
    }

    @Test
    public void test_solution_5() {
        String s = "100[leetcode]";
        Assert.assertEquals("leetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcode", decodeString(s));
    }

}
