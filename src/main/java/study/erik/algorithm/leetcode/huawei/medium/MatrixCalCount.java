package study.erik.algorithm.leetcode.huawei.medium;

import java.util.Scanner;
import java.util.Stack;

//HJ70 矩阵乘法计算量估算
//https://www.nowcoder.com/practice/15e41630514445719a942e004edc0a5b?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
public class MatrixCalCount {

    /*
3
50 10
10 20
20 5
(A(BC))

* */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int[][] data = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] line = in.nextLine().split(" ");
            data[i][0] = Integer.parseInt(line[0]);
            data[i][1] = Integer.parseInt(line[1]);
        }
        long num = 0;
        String order = in.nextLine();
        Stack<int[]> stack = new Stack<>();
        int alphaIndex = 0;
        for (int i = 0; i < order.length(); i++) {
            char ch = order.charAt(i);
            if (ch == '(') {
                // 都不用入栈
                continue;
            }
            if (Character.isAlphabetic(ch)) {
                //是字母
                stack.push(new int[]{alphaIndex, alphaIndex});
                alphaIndex++;
                continue;
            }
            // ')' 触发计算
            int[] to = stack.pop();
            int[] from = stack.pop();
            // 把计算之后的新的(from,to)入栈
            stack.push(new int[]{from[0], to[1]});
            int a = data[from[0]][0];
            int b = data[from[1]][1];
            int c = data[to[1]][1];
            num += ((long) a * b * c);
        }
        System.out.println(num);
    }

}
