/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bit;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yueyi
 * @version : MaximumXORWithAnElementFromArray.java, v 0.1 2023年06月21日 08:13 yueyi Exp $
 */
public class MaximumXORWithAnElementFromArray {

    @LetCodeCommit(title = "1707. Maximum XOR With an Element From Array")
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int queriesLength = queries.length;
        int[] ans = new int[queriesLength];
        int[][] temp = new int[queriesLength][3];
        for (int i = 0; i < queriesLength; i++) {
            temp[i][0] = queries[i][0];
            temp[i][1] = queries[i][1];
            temp[i][2] = i;
        }

        Arrays.sort(temp, Comparator.comparingInt(a -> a[1]));
        int index = 0;
        Arrays.sort(nums);
        TrieNode root = new TrieNode();

        for (int[] query : temp) {
            //temp是按照查询大小排过序了，这里从小到大来构造其解法
            while (index < nums.length && nums[index] <= query[1]) {
                //按照需要加载树，这是一个必要的动作。所以，也这可以看做是一个离线题目。
                insert(root, nums[index]);
                index++;
            }
            int tempAns = -1;
            if (index != 0) {
                tempAns = search(root, query[0]);
            }
            ans[query[2]] = tempAns;
        }

        return ans;
    }

    public void insert(TrieNode root, int n) {
        //构造这颗bit数
        TrieNode node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (n >> i) & 1;
            if (node.nums[bit] == null) {
                node.nums[bit] = new TrieNode();
            }
            node = node.nums[bit];
        }
        node.prefixValue = n;
    }

    public int search(TrieNode root, int n) {
        //按照当前的树，查询哪个值最合适与n做XOR(其结果值最大)。
        TrieNode node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (n >> i) & 1;
            int requiredBit = bit == 1 ? 0 : 1;
            if (node.nums[requiredBit] != null) {
                node = node.nums[requiredBit];
            } else {
                node = node.nums[bit];
            }
        }
        return node.prefixValue ^ n;
    }

    class TrieNode {
        TrieNode nums[] = new TrieNode[2];
        int      prefixValue; //其实冗余了，因为只有到叶子节点才会有这个值。
    }

}