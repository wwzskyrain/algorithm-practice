package study.erik.algorithm.leetcode.huawei.medium;

import java.util.*;

//HJ20 密码验证合格程序
// https://www.nowcoder.com/practice/184edec193864f0985ad2684fbc86841?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
// 也没有多少难度，只需要注意一点：不能分割出两个独立的、长度大于2 的连续子串，使得这两个子串完全相同；
// 这里处理注意两点：
// 1. 只需要比较length=3的所有子串即可；
// 2. 还要注意重叠对比的情况，比如ababa，中aba、与第二个aba中间重叠了一个a，做一不符合条件。
//    这里如何表达呢，就要记录sub的lastIndex，如果大于2了在认为是符合了‘子串相同的条件’
public class CheckPassword {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String password = in.nextLine();
            String ret = "NG";
            if (doCheck(password)) {
                ret = "OK";
            }
            System.out.println(ret);
        }
    }

    public static boolean doCheck(String p) {
        if (p.length() < 8) {
            return false;
        }
        Map<String, Integer> subStrMap = new HashMap<>();
        boolean[] type = new boolean[4];
        // 大写字母，小写字母，数字，特殊字符
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (!type[0]) {
                type[0] = c >= 'A' && c <= 'Z';
            }
            if (!type[1]) {
                type[1] = c >= 'a' && c <= 'z';
            }
            if (!type[2]) {
                type[2] = c >= '0' && c <= '9';
            }
            if (!type[3]) {
                type[3] = (c >= '!' && c <= '/') || (c >= ':' && c <= '@') || (c >= '[' && c <= '`') || (c >= '{' && c <= '~');
            }
            if (i > 1) {
                String sub = p.substring(i - 2, i + 1);
                if (subStrMap.containsKey(sub)) {
                    int idx = subStrMap.get(sub);
                    if (i - idx > 2) {
                        return false;
                    }
                } else {
                    subStrMap.put(sub, i);
                }
            }
        }
        int typeNum = 0;
        for (int i = 0; i < type.length; i++) {
            if (type[i]) {
                typeNum++;
            }
        }
        return typeNum >= 3;
    }
}
