package study.erik.algorithm.nowcoder.zijie;

import java.util.Scanner;

/**
 * @author erik.wang
 * @date 2020-07-19 17:25
 */
public class CorrectWord {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer n = Integer.valueOf(scanner.nextLine());
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = scanner.nextLine();
        }

        String[] correctedWords = correctWords(words);
        for (String correctedWord : correctedWords) {
            System.out.println(correctedWord);
        }

    }

    /**
     * 这个题目，让我想到了状态机，而且状态机的状态，这里用了subStr的长度
     *
     * @param words
     * @return
     */
    public static String[] correctWords(String[] words) {

        char[] subStr = new char[4];

        String[] correctedWords = new String[words.length];

        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder();
            String word = words[i];
            int index = 0;
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (index == 0) {
                    subStr[index++] = c;
                    sb.append(c);
                } else if (index == 1) {
                    sb.append(c);
                    if (c == subStr[index - 1]) {
                        subStr[index++] = c;
                    } else {
                        subStr[0] = c;
                    }
                } else if (index == 2) {
                    if (c == subStr[index - 1]) {
                        continue;
                    } else {
                        subStr[index++] = c;
                        sb.append(c);
                    }
                } else if (index == 3) {
                    if (c == subStr[index - 1]) {
                        continue;
                    } else {
                        sb.append(c);
                        index = 0;
                        subStr[index++] = c;
                    }
                }
            }
            correctedWords[i] = sb.toString();
        }
        return correctedWords;
    }

}
