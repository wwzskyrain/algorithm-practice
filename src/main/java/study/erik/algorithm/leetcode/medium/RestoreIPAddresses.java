package study.erik.algorithm.leetcode.medium;

import org.junit.Test;

import javax.naming.ldap.PagedResultsControl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author erik.wang
 * @date 2020-04-20 10:06
 */
public class RestoreIPAddresses {

    public List<String> restoreIpAddresses(String s) {
        return solution(s);
    }

    /**
     * 思路：Ip格式是固定的，所以没必要用dfs这种重量级的方法
     * 可以分别用四个循环做
     * 成绩：5和6，什么，不可思议；
     * 然后看了一下投票最多的一个解答，思路和我一样一样的；算了，不要看成绩，虚荣。
     *
     * @param s
     * @return
     */
    public List<String> solution(String s) {

        if (s.length() < 4 || s.length() > 12) {
            return new ArrayList<>();
        }
        Set<String> result = new HashSet<>();
        for (int i1 = 0; i1 < 4 && i1 < s.length(); i1++) {
            String ip1 = s.substring(0, i1 + 1);
            if (!valid(ip1)) {
                continue;
            }
            for (int i2 = i1 + 1; i2 < i1 + 4 && i2 < s.length(); i2++) {
                String ip2 = s.substring(i1 + 1, i2 + 1);
                if (!valid(ip2)) {
                    continue;
                }
                for (int i3 = i2 + 1; i3 < i2 + 4 && i3 < s.length(); i3++) {

                    String ip3 = s.substring(i2 + 1, i3 + 1);
                    if (!valid(ip3)) {
                        continue;
                    }

                    String ip4 = s.substring(i3 + 1);
                    if (valid(ip4)) {
                        result.add(String.format("%s.%s.%s.%s", ip1, ip2, ip3, ip4));
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }

    public boolean valid(String ip) {
        if (ip.length() < 1 || ip.length() > 3) {
            return false;
        }
        if (ip.length() > 1 && ip.startsWith("0")) {
            return false;
        }
        return Integer.parseInt(ip) < 256;
    }

    @Test
    public void test_() {

        System.out.println(restoreIpAddresses("010010"));
        System.out.println(restoreIpAddresses("00000"));
        System.out.println(restoreIpAddresses("25525511135"));
    }
}
