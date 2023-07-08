/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.bit;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : CountPairsWithXORInARange.java, v 0.1 2023年07月02日 20:35 yueyi Exp $
 */
@LetCodeCommit(title = "1803. Count Pairs With XOR in a Range",
        diff = "h",
        selfRemark = "字典树就不用说了，已经很熟悉了。"
                + "知识点在于字典树可以用来计数，和bit结合，可以很快计算出当前树中位于区间的个数。")
public class CountPairsWithXORInARange {

    private static int MAX_BIT = 14;

    public int countPairs(int[] nums, int low, int high) {
        int ret = 0;
        TireNode root = new TireNode();
        for (int num : nums) {
            ret += (calCntLessThanK(root, num, high + 1) - calCntLessThanK(root, num, low));
            insert(root, num);
        }
        return ret;
    }

    public int calCntLessThanK(TireNode root, int n, int k) {
        int total = 0;
        for (int i = MAX_BIT; i >= 0 && root != null; i--) {
            int bitK = (k >> i) & 1;
            int bitN = (n >> i) & 1;
            if (bitK == 1) {
                // 当前位是1，则计算异或之后是0的，而根据异或逻辑，当前位和bitN相同，则异或结果为0.
                if (root.children[bitN] != null) {
                    total += root.children[bitN].cnt;
                }
                // 异或结果为0的已经在上面囊括完了。要在去检查异或结果为1的，所以逆异或，取1-bitN。
                root = root.children[1 - bitN];
            } else {
                // 当前位是0，则不需加入计算；访问下一位 —— bitN^bitN = 0；
                root = root.children[bitN];
            }
        }
        return total;
    }

    public static class TireNode {
        public TireNode[] children;
        public int        cnt;

        public TireNode() {
            this.children = new TireNode[2];
            this.cnt = 0;
        }
    }

    public void insert(TireNode root, int n) {
        for (int i = MAX_BIT; i >= 0; i--) {
            int bit = (n >> i) & 1;
            if (root.children[bit] == null) {
                root.children[bit] = new TireNode();
            }
            root.children[bit].cnt++;
            root = root.children[bit];
        }
    }

}