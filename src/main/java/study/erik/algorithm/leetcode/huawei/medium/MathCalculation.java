package study.erik.algorithm.leetcode.huawei.medium;


import java.util.Scanner;
import java.util.Stack;

//HJ50 四则运算
//https://www.nowcoder.com/practice/9999764a61484d819056f807d2a91f1e?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
//1. 当使用Stack时，不要用非栈的方法，比如getLast()，要用peek；不要用removeLast，要用pop
//2. 关于表达式的计算
public class MathCalculation {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        Stack<Character> opt = new Stack<Character>();
        Stack<Integer> data = new Stack<Integer>();
        int i = 0;
        int addOrSub = 1;
        // 替换一下，简化后续的处理逻辑
        str = str.replaceAll("\\{", "(");
        str = str.replaceAll("\\[", "(");
        str = str.replaceAll("}", ")");
        str = str.replaceAll("]", ")");
        while (i < str.length()) {
            char c = str.charAt(i);
            if (c == '-' && (i == 0 || str.charAt(i - 1) == '(')) {
                // 单纯的负号
                addOrSub = -1;
                i++;
                continue;
            }
            if (isOp(c)) {
                switch (c) {
                    case '(':
                        opt.push(c);
                        i++;
                        break;
                    case ')':
                        // 特殊处理，因为要进行括号的湮灭操作，不是标准的cal操作。
                        char lastO = opt.peek();
                        if (lastO == '(') {
                            // 括号匹配并闭合
                            opt.pop();
                            i++;
                        } else {
                            cal(data, opt);
                        }
                        break;
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        // 【重要】：优先级绝对高，就进栈，否则就计算；
                        if (!opt.isEmpty() && (priority(c) <= priority(opt.peek()))) {
                            cal(data, opt);
                        } else {
                            opt.push(c);
                            i++;
                        }
                        break;
                    default:
                        // nothing or throw exception;

                }
            } else {
                // 数字字母，
                int j = i;
                int num = 0;
                // 处理连续的数字字符
                while (j < str.length() && !isOp(str.charAt(j))) {
                    num *= 10;
                    num += str.charAt(j) - '0';
                    j++;
                }
                data.push(addOrSub * num);
                i = j; // 保持i跟进
                addOrSub = 1;
            }
        }
        while (!opt.isEmpty()) {
            cal(data, opt);
        }
        System.out.println(data.peek());
    }

    public static int priority(char c) {
        switch (c) {
            case '(':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                // '(' 的优先级更低；优先级越低越要进栈，优先级越高越要计算
                return 0;
        }
    }

    public static void cal(Stack<Integer> data, Stack<Character> opt) {
        char o = opt.pop();
        int b = data.pop();
        int a = data.pop();
        switch (o) {
            case '+':
                data.push(a + b);
                break;
            case '-':
                data.push(a - b);
                break;
            case '*':
                data.push(a * b);
                break;
            case '/':
                data.push(a / b);
                break;
            default:
                // nothing
        }
    }

    public static boolean isOp(char c) {
        return c == '{' || c == '}' || c == '[' || c == ']' || c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '/';
    }
}
