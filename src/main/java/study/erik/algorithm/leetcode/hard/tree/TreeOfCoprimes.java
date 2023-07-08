/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.tree;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yueyi
 * @version : TreeOfCoprimes.java, v 0.1 2023年07月02日 17:51 yueyi Exp $
 */
@LetCodeCommit(title = "1766. Tree of Coprimes")
public class TreeOfCoprimes {

    /*
        Declared Global to make method signature simple and readable.
        But can be passed in method signature to get rid of Global variables
    */
    final int MAX_CO_PRIME = 51;
    final int MAX          = Integer.MAX_VALUE;

    boolean[][]         coPrimeMemo;
    List<Integer>       graph[];
    // Each entry will represent: <value, [depth, nodeIndex]>
    Map<Integer, int[]> ancestor;

    /*
    Euclidean algorithm to find GCD
    https://en.wikipedia.org/wiki/Euclidean_algorithm
    */
    public int gcd(int a, int b) {
        if (b == 0) {return a;}
        return gcd(b, a % b);
    }

    public void dfs(int node, int[] result, int parent, int depth, int[] nums) {

        int depthOfAncestor = MAX;
        int closestAncestor = -1;
        /*
            Find a number (candidate) that is Co-prime with current node value and also check
            if we have that candidate in out 'ancestor' map.
            If found, try to minimize the depth between them.
        */
        for (int candidate = 1; candidate < MAX_CO_PRIME; candidate++) {
            if (coPrimeMemo[candidate][nums[node]] && ancestor.containsKey(candidate)) {
                if (depthOfAncestor > depth - ancestor.get(candidate)[0]) {
                    depthOfAncestor = depth - ancestor.get(candidate)[0];
                    closestAncestor = ancestor.get(candidate)[1];
                }
            }
        }
        // Store the closest ancestor found, If not, by default -1
        result[node] = closestAncestor;

        // for backtracking purpose, store old entry so that we can restore it back
        // when we come again after visiting its children.
        // If No such entry in our ancestor, we store [-1, -1] (distinguishing value)
        int[] oldEntry = ancestor.containsKey(nums[node]) ? ancestor.get(nums[node]) : new int[] {-1, -1};

        ancestor.put(nums[node], new int[] {depth, node});
        // Visit its child just like we do in DFS. Just skip the parent
        for (int child : graph[node]) {
            if (child == parent) {continue;}
            dfs(child, result, node, depth + 1, nums);
        }
        // backtracking : restrore old entry if present
        if (oldEntry[0] != -1) {
            ancestor.put(nums[node], oldEntry);
        } else {
            ancestor.remove(nums[node]);
        }

    }

    public int[] getCoprimes(int[] nums, int[][] edges) {
        coPrimeMemo = new boolean[MAX_CO_PRIME][MAX_CO_PRIME];
        int n = nums.length;
        int[] result = new int[n];
        graph = new ArrayList[n];
        // Each entry in ancestor will represent: <value, [depth, nodeIndex]>
        ancestor = new HashMap<>();
        /* Pre compute Co-primes as the constraints are small */
        for (int a = 1; a < MAX_CO_PRIME; a++) {
            for (int b = 1; b < MAX_CO_PRIME; b++) {
                if (gcd(a, b) == 1) {
                    coPrimeMemo[a][b] = coPrimeMemo[b][a] = true;
                }
            }
        }
        /* Create a graph for easy access of children */
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        dfs(0, result, -1, 0, nums);
        return result;
    }

}