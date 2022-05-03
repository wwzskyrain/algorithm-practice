/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.union;

import org.junit.Test;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : AccountsMerge.java, v 0.1 2022年05月03日 11:17 yueyi Exp $
 */
public class AccountsMerge {

    @LetCodeCommit(title = "721. Accounts Merge",
            selfRemark = "就是冲着并查集去的.")
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        int[] parents = new int[accounts.size()];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        Map<String, Integer> mail2IndexMap = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String mail = account.get(j);
                if (mail2IndexMap.containsKey(mail)) {
                    Integer index = mail2IndexMap.get(mail);
                    int p1 = findParent(parents, index);
                    int p2 = findParent(parents, i);
                    if (p1 != p2) {
                        union(parents, p1, p2);
                    }
                } else {
                    mail2IndexMap.put(mail, i);
                }
            }
        }

        Map<Integer, Set<String>> index2MergeMailsMap = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            int parent = findParent(parents, i);
            index2MergeMailsMap.putIfAbsent(parent, new HashSet<>());
            index2MergeMailsMap.get(parent).addAll(account.subList(1, account.size()));
        }

        return index2MergeMailsMap.entrySet()
                .stream()
                .map((entry) -> {
                    List<String> mergeMailList = entry.getValue()
                            .stream()
                            .sorted()
                            .collect(Collectors.toList());
                    mergeMailList.add(0, accounts.get(entry.getKey()).get(0));
                    return mergeMailList;
                }).collect(Collectors.toList());
    }

    private int findParent(int[] parent, int i) {
        while (parent[i] != i) {
            i = parent[i];
        }
        return i;
    }

    private void union(int[] parent, int i, int j) {
        int parentI = findParent(parent, i);
        int parentJ = findParent(parent, j);
        if (parentI != parentJ) {
            parent[parentI] = parentJ;
        }
    }

    public static void main(String[] args) {

    }

    @Test
    public void test() {
        List<List<String>> accounts = ArrayUtils.buildStrList2Dimension(
                "[[\"John\",\"johnsmith@mail.com\",\"john_newyork@mail.com\"],[\"John\",\"johnsmith@mail.com\","
                        + "\"john00@mail.com\"],[\"Mary\",\"mary@mail.com\"],[\"John\",\"johnnybravo@mail.com\"]]");

        List<List<String>> lists = accountsMerge(accounts);
        lists.stream().forEach(System.out::println);

    }

}