package study.erik.algorithm.leetcode.hard.dp;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/8/3 ，时间：13:19
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class TheScoreOfStudentsSolvingMathExpression {

    @LetCodeCommit(title = "2019. The Score of Students Solving Math Expression", diff = "h", selfRemark = "是一个dp题目，是一个对角dp题目。题解写的很清楚——图很漂亮： https://leetcode.com/problems/the-score-of-students-solving-math-expression/solutions/1486306/python-java-explanation-with-pictures-dp/" + "1.我这里改进了它对s表达式的计算")
    public int scoreOfStudents(String s, int[] answers) {

        int n = s.length() / 2 + 1;
        Set<Integer>[][] res = new Set[n][n];
        for (int i = 0; i < n; i++) {
            res[i][i] = new HashSet<>();
            res[i][i].add(s.charAt(2 * i) - '0');
        }
        for (int diff = 1; diff < n; diff++) {
            for (int start = 0; start < n - diff; start++) {
                int end = start + diff;
                res[start][end] = new HashSet<>();
                for (int i = start * 2 + 1; i < end * 2; i += 2) {
                    if (s.charAt(i) - '+' == 0) {
                        for (int a : res[start][(int) (i / 2)]) {
                            for (int b : res[(int) (i / 2 + 1)][end]) {
                                if (a + b <= 1000) res[start][end].add(a + b);
                            }
                        }
                    } else {
                        for (int a : res[start][(int) (i / 2)]) {
                            for (int b : res[(int) (i / 2 + 1)][end]) {
                                if (a * b <= 1000) res[start][end].add(a * b);
                            }
                        }
                    }
                }
            }
        }

        int correctAnswer = dfsCalculate(s);
        Set<Integer> allPossibleAnswer = res[0][n - 1];
        int ans = 0;
        for (int answer : answers) {
            ans += (correctAnswer == answer ? 5 : allPossibleAnswer.contains(answer) ? 2 : 0);
        }
        return ans;
    }

    public static int dfsCalculate(String exp) {
        if (exp.length() == 0) {
            return 0;
        }
        int n = exp.charAt(0) - '0';
        int optIndex = 1;
        while (optIndex < exp.length() && exp.charAt(optIndex) == '*') {
            int n2 = exp.charAt(optIndex + 1) - '0';
            n *= n2;
            optIndex += 2;
        }
        return n + dfsCalculate(exp.substring(Math.min(exp.length(), optIndex + 1)));
    }

    public int calculate(String s) {
        int i = 0;
        Stack<Integer> stack = new Stack<>();
        char operator = '+';
        int num = 0;
        while (i < s.length()) {
            char ch = s.charAt(i++);
            if (ch >= '0' && ch <= '9') num = ch - '0';
            if (i >= s.length() || ch == '+' || ch == '*') {
                if (operator == '+') stack.push(num);
                else if (operator == '*') stack.push(stack.pop() * num);
                operator = ch;
                num = 0;
            }
        }
        return stack.stream().mapToInt(Integer::intValue).sum();
    }

//    public static void main(String[] args) {
//        System.out.println(dfsCalculate("1+3*5+7*2"));
//        System.out.println(dfsCalculate("1+3+5+7*2"));
//    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{{7, "7+3*1*2", ArrayUtils.buildArray("[20,13,42]")}, {19, "3+5*2", ArrayUtils.buildArray("[13,0,10,13,13,16,16]")}, {10, "6+0*1", ArrayUtils.buildArray("[12,9,6,4,8,6]")},});
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public String s;
    @Parameterized.Parameter(2)
    public int[] answers;


    @Test
    public void test() {
        Assert.assertEquals(expect, scoreOfStudents(s, answers));
    }

}
