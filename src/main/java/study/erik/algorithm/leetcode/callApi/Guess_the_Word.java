package study.erik.algorithm.leetcode.callApi;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

@LetCodeCommit(title = "843. Guess the Word",
        selfRemark = "这个猜单词游戏，还用了一个'启发式极小化极大算法'，感觉没啥意思——看不懂。" +
                "启发个啥子。" +
                "然后，这个题目好多人根据一个条件就能做出来了：找匹配度相同的就行啦。哈哈。")
public class Guess_the_Word {
    int[][] H;

    public void findSecretWord(String[] wordlist, Master master) {
        int N = wordlist.length;
        H = new int[N][N];
        for (int i = 0; i < N; ++i)
            for (int j = i; j < N; ++j) {
                int match = 0;
                for (int k = 0; k < 6; ++k)
                    if (wordlist[i].charAt(k) == wordlist[j].charAt(k)) match++;
                H[i][j] = H[j][i] = match;
            }

        List<Integer> possible = new ArrayList();
        List<Integer> path = new ArrayList();
        for (int i = 0; i < N; ++i) possible.add(i);

        while (!possible.isEmpty()) {
            int guess = solve(possible, path);
            int matches = master.guess(wordlist[guess]);
            if (matches == wordlist[0].length()) return;
            List<Integer> possible2 = new ArrayList();
            for (Integer j : possible) {
                if (H[guess][j] == matches) {
                    possible2.add(j);
                }
            }
            possible = possible2;
            path.add(guess);
        }
    }

    public int solve(List<Integer> possible, List<Integer> path) {
        if (possible.size() <= 2) return possible.get(0);
        List<Integer> ansgrp = possible;
        int ansguess = -1;
        //每一个guess点都会有一个数组，所以算法这里需要找到这些guess对应的数组的最大值最小的那个数组——对应的guess点。
        //至于这样的点，有什么好处，就不得而知了。
        for (int guess = 0; guess < H.length; ++guess) {
            if (!path.contains(guess)) {
                //猜过的，就不猜了。
                ArrayList<Integer>[] groups = new ArrayList[7];
                for (int i = 0; i < 7; ++i) groups[i] = new ArrayList<Integer>();
                for (Integer j : possible) {
                    if (j != guess) {
                        groups[H[guess][j]].add(j);
                    }
                }

                ArrayList<Integer> maxgroup = groups[0];
                for (int i = 0; i < 7; ++i) {
                    if (groups[i].size() > maxgroup.size()) {
                        maxgroup = groups[i];
                    }
                }

                if (maxgroup.size() < ansgrp.size()) {
                    ansgrp = maxgroup;
                    ansguess = guess;
                }
            }
        }

        return ansguess;
    }


    public static class Master {
        public int guess(String s) {
            return -1;
        }
    }

}
