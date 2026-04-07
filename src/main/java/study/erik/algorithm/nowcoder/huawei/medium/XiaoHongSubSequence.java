package study.erik.algorithm.nowcoder.huawei.medium;

import java.util.Scanner;

//HJ117 小红的01子序列构造（easy）
//https://www.nowcoder.com/practice/ee0b6c6baa2642c182df8b4390126f9a?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
public class XiaoHongSubSequence {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] firstLine = in.nextLine().split(" ");
        long n = Long.parseLong(firstLine[0]);
        long k = Long.parseLong(firstLine[1]);
        String str = in.nextLine();
        int i = 0;
        int j = 1; // 注意：要预先计算j=0的，也就是窗口[0,0]
        char c0 = str.charAt(0);
        int count0 = (c0 == '0' ? 1 : 0);
        int count1 = (c0 == '0' ? 0 : 1);
        long sum = 0;
        while (j < n) {
            if (sum < k) {
                if (str.charAt(j) == '0') {
                    count0++;
                } else {
                    count1++;
                    sum += count0;
                }
                j++;
            } else if (sum > k) {
                if (str.charAt(i) == '0') {
                    count0--;
                    sum -= count1;
                } else {
                    count1--;
                }
                i++;
            } else {
                int a = i;
                int b = j - 1;
                // [a,b]才是找到的区间，然后从1开始计算下班，就变成了[a+1, b+1]
                System.out.printf("%d %d", a + 1, b + 1);
                return;
            }
        }
        System.out.println("-1");

    }
}
