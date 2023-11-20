package study.erik.algorithm.job.huawei.real;

import java.util.*;

public class Main_1 {

    public static void main(String[] args) {
        System.out.println(convert("12345678", 3));
        System.out.println(convert("12345678", 4));
    }

    /**
     * 把s进行N行重排，然后重新收集
     */
    public static String convert(String s, int numRows) {
        List<Character>[] lists = new List[numRows];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList<>();
        }
        int row = 0;
        boolean down = false;
        for (int i = 0; i < s.length(); i++) {
            List<Character> curList = lists[row];
            curList.add(s.charAt(i));
            if (row == numRows - 1) {
                down = !down;
            } else if (row == 0) {
                down = !down;
            }
            row = row + (down ? 1 : -1);
        }
        StringBuilder sb = new StringBuilder();
        for (List<Character> list : lists) {
            list.forEach(c -> sb.append(c));
        }
        return sb.toString();
    }

}
