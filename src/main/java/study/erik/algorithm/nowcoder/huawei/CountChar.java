package study.erik.algorithm.nowcoder.huawei;

import java.util.Scanner;

// 计算某字符出现次数
public class CountChar {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String s = in.nextLine();
        String c = in.nextLine();
        char targetChar = c.charAt(0);
        int cout = 0;
        if (targetChar >= '0' && targetChar <= '9') {
            for (int i = 0; i < s.length(); i++) {
                if (targetChar == s.charAt(i)) {
                    cout++;
                }
            }
        } else {
            int diff = 0;
            if (targetChar >= 'a' && targetChar <= 'z') {
                diff = targetChar - 'a';
            } else {
                diff = targetChar - 'A';
            }
            for (int i = 0; i < s.length(); i++) {
                char cc = s.charAt(i);
                if (cc >= 'a' && cc <= 'z' && cc - 'a' == diff) {
                    cout++;
                } else {
                    if (cc - 'A' == diff) {
                        cout++;
                    }
                }
            }
        }
        System.out.println(cout);
    }

}
