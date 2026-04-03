package study.erik.algorithm.leetcode.huawei.medium;

import java.util.*;

//HJ27 查找兄弟单词
//https://www.nowcoder.com/practice/03ba8aeeef73400ca7a37a5f3370fe68?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
// 有一个字符串题目，很简单~
public class LookBrotherWord {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] line = in.nextLine().split(" ");
        int s = Integer.parseInt(line[0]);
        int l = line.length;
        String x = line[l - 2];
        int k = Integer.parseInt(line[l - 1]);
        List<String> broWords = new ArrayList<>();
        int[] target = getCharNum(x);
        for (int i = 1; i < l - 2; i++) {
            String word = line[i];
            if (word.compareTo(x) == 0) {
                continue;
            }
            if (sameCharNum(target, getCharNum(word))) {
                broWords.add(word);
            }
        }
        broWords.sort(String::compareTo);
        int same = broWords.size();
        System.out.println(same);
        if (same > 0 && k - 1 < same) {
            System.out.println(broWords.get(k - 1));
        }
    }

    public static int[] getCharNum(String s) {
        int[] charNum = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            charNum[c - 'a']++;
        }
        return charNum;
    }

    public static boolean sameCharNum(int[] target, int[] charNum) {
        if (target.length != charNum.length) {
            return false;
        }
        for (int i = 0; i < target.length; i++) {
            if (target[i] != charNum[i]) {
                return false;
            }
        }
        return true;
    }

}

