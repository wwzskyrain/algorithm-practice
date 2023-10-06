package study.erik.algorithm.leetcode.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Interval {
    public int start;
    public int end;

    public Interval() {
    }

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }

    @Override
    public String toString() {
        return String.format("[%d,%d]", start, end);
    }

    /**
     * @param input [[[1,2],[5,6]],[[1,3]],[[4,10]]]
     * @return
     */
    public static List<List<Interval>> buildList2Dimension(String input) {
        input = input.replace(" ", "");

        List<List<Interval>> ret = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int n = input.length();
        for (int i = 0; i < n; i++) {
            char c = input.charAt(i);
            switch (c) {
                case '[':
                    stack.push(i);
                    break;
                case ']':
                    Integer pop = stack.pop();
                    if (stack.size() == 1) {
                        ret.add(buildList(input.substring(pop, i)));
                    }
                    break;
                default:
            }
        }
        return ret;
    }

    /**
     * @param input [1,2],[5,6]
     * @return
     */
    public static List<Interval> buildList(String input) {
        Stack<Integer> stack = new Stack<>();
        List<Interval> ret = new ArrayList<>();
        int n = input.length();
        for (int i = 0; i < n; i++) {
            char c = input.charAt(i);
            switch (c) {
                case '[':
                    stack.push(i);
                    break;
                case ']':
                    Integer pop = stack.pop();
                    ret.add(buildInterval(input.substring(pop + 1, i)));
                    break;
                default:
            }
        }
        return ret;
    }

    /**
     * @param input 5,6
     * @return
     */
    public static Interval buildInterval(String input) {
        String[] split = input.split(",");
        int x = Integer.valueOf(split[0]);
        int y = Integer.valueOf(split[1]);
        return new Interval(x, y);
    }


    public static void main(String[] args) {
        List<List<Interval>> lists = buildList2Dimension("[[[1,2],[5,6]],[[1,3]],[[4,10]]]");
        System.out.println(lists);

        System.out.println(buildList("[3,4]"));
    }
};