package study.erik.algorithm.nowcoder.zijie;

import java.util.Scanner;

/**
 * @author erik.wang
 * @date 2020-07-23 00:25
 * url = https://www.nowcoder.com/question/next?pid=16516564&qid=362291&tid=35031245
 */
public class Mahjon {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] cards = new int[14];
        int[] count = new int[10];
        for (int i = 0; i < 13; i++) {
            cards[i] = scanner.nextInt();
            count[cards[i]]++;
        }

        int[] result = new int[9];
        int sum = 0;
        for (int i = 1; i < count.length; i++) {
            if (count[i] < 4) {
                cards[13] = i;
                if (winCard(cards, new boolean[14], new int[14], 0)) {
                    result[sum++] = i;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sum; i++) {
            sb.append(result[i]).append(" ");
        }
        if (sb.length() == 0) {
            System.out.println(0);
        } else {
            System.out.println(sb.toString().trim());
        }

    }

    /**
     * 14张card是否可以和牌?
     * <p>
     * 有点状态记得感觉，其实人家叫做'回溯法'
     * 1.   先找雀头，sIn = 0，1
     * 2.   在找顺子或者刻字，sIn = (2，3，4),(5,6,7),(8,9,10),(11,12,13)
     *
     * @param cards
     * @param visited
     * @param stack   牌的一种组合
     * @param sIn     stack的指针
     * @return true，14张card可以和牌；false 不能和牌
     */
    private static boolean winCard(int[] cards, boolean[] visited, int[] stack, int sIn) {
        if (sIn == cards.length) {
            return true;
        }
        for (int i = 0; i < cards.length; i++) {
            if (visited[i]) {
                continue;
            }
            boolean win;
            switch (sIn) {
                case 0:
                    stack[sIn] = cards[i];
                    visited[i] = true;
                    win = winCard(cards, visited, stack, sIn + 1);
                    if (win) {
                        //快速返回
                        return true;
                    }
                    //回溯
                    visited[i] = false;
                    break;
                case 1:
                    if (cards[i] == stack[sIn - 1]) {
                        stack[sIn] = cards[i];
                        visited[i] = true;
                        win = winCard(cards, visited, stack, sIn + 1);
                        if (win) {
                            return true;
                        }
                        visited[i] = false;
                    }
                    break;
                case 2:
                case 5:
                case 8:
                case 11:
                    //新的顺子、刻子的开始，直接进栈并回溯即可
                    stack[sIn] = cards[i];
                    visited[i] = true;
                    win = winCard(cards, visited, stack, sIn + 1);
                    if (win) {
                        return true;
                    }
                    visited[i] = false;
                    break;
                case 3:
                case 6:
                case 9:
                case 12:
                    //顺子模式 或者 刻子模式
                    if (stack[sIn - 1] == cards[i] || stack[sIn - 1] + 1 == cards[i]) {
                        stack[sIn] = cards[i];
                        visited[i] = true;
                        win = winCard(cards, visited, stack, sIn + 1);
                        if (win) {
                            return true;
                        }
                        visited[i] = false;
                    }
                    break;
                case 4:
                case 7:
                case 10:
                case 13:
                    int s1 = stack[sIn - 1];
                    int s2 = stack[sIn - 2];
                    int c = cards[i];
                    //刻子模式
                    if (s1 == s2) {
                        if (c == s1) {
                            // push stack
                            stack[sIn] = c;
                            visited[i] = true;
                            win = winCard(cards, visited, stack, sIn + 1);
                            if (win) {
                                return true;
                            }
                            visited[i] = false;
                        }
                    } else {
                        //刻子模式
                        if (c == s1 + 1) {
                            //push stack
                            stack[sIn] = c;
                            visited[i] = true;
                            win = winCard(cards, visited, stack, sIn + 1);
                            if (win) {
                                return true;
                            }
                            visited[i] = false;
                        }
                    }
                    break;
                default:
                    // never default.
            }
        }
        //唯一返回false的地方
        return false;
    }
}
