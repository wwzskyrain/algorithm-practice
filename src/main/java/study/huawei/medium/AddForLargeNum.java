package study.huawei.medium;

import java.util.*;

//HJ57 高精度整数加法
//https://www.nowcoder.com/practice/49e772ab08994a96980f9618892e55b6?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
public class AddForLargeNum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        String b = in.nextLine();
        int i = a.length() - 1;
        int j = b.length() - 1;
        Stack<Character> stack = new Stack<>();
        int flow = 0;
        while (i >= 0 && j >= 0) {
            // 从低位开始加（也就是字符串的逆序访问）
            int r = a.charAt(i) - '0' + (b.charAt(j) - '0') + flow;
            char cur = (char) ('0' + (r % 10));
            flow = r / 10;
            stack.push(cur);
            i--;
            j--;
        }
        while (i >= 0) {
            // a还有更高位的数组字符要加上
            int r = a.charAt(i) - '0' + flow;
            char cur = (char) ('0' + (r % 10));
            flow = r / 10;
            stack.push(cur);
            i--;
        }

        while (j >= 0) {
            // b还有更高位的数组字符要加上
            int r = (b.charAt(j) - '0') + flow;
            char cur = (char) ('0' + (r % 10));
            flow = r / 10;
            stack.push(cur);
            j--;
        }
        if (flow > 0) {
            // 别漏掉这个进位，最后的尾巴
            stack.push('1');
        }

        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb);
    }

}
