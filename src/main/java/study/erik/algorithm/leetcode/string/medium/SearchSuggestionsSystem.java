/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string.medium;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * @author yueyi
 * @version : SearchSuggestionsSystem.java, v 0.1 2023年03月17日 03:09 yueyi Exp $
 */
public class SearchSuggestionsSystem {

    @LetCodeCommit(title = "1268. Search Suggestions System",
            selfRemark = "用treeMap来排序并查找，应该是很不错的。不要忘了这个利器哈")
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {

        TreeMap<String, Integer> map = new TreeMap<>();
        List<String> list = Arrays.asList(products);
        list.sort(String::compareTo);
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), i);
        }

        List<List<String>> ret = new ArrayList<>();
        int n = searchWord.length();
        String pre = "";
        for (int i = 0; i < n; i++) {
            pre = pre + searchWord.charAt(i);
            String preX = pre + "~";
            String ceilingKey = map.ceilingKey(pre);
            String floorKey = map.floorKey(preX);
            if (ceilingKey == null || floorKey == null) {
                ret.add(new ArrayList<>());
                continue;
            }
            Integer f = map.get(ceilingKey);
            ret.add(list.subList(f, Math.min(f + 3, map.get(floorKey) + 1)));
        }
        return ret;
    }
}