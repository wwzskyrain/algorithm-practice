/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.tree;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yueyi
 * @version : DeleteDuplicateFoldersInSystem.java, v 0.1 2023年07月27日 14:24 yueyi Exp $
 */
@LetCodeCommit(title = "1948. Delete Duplicate Folders in System",
        selfRemark = "我觉得这个题目很low，所以我在理解了这个解法之后，就直接copy了。"
                + "为啥low呢？"
                + "题意要求删除有相同后缀的路径——题意描述起来繁杂。"
                + "这个解法low："
                + "构造树是必须的，比较key的个数也是必须的。"
                + "但是没有比较非叶子节点的key。")
public class DeleteDuplicateFoldersInSystem {

    Folder               root = new Folder("");
    Map<String, Integer> keys = new HashMap<>();

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        for (List<String> path : paths) {
            addPath(path);
        }

        for (Folder f : root.list) {
            generateKey(f);
        }

        for (Folder f : root.list) {
            updateDeleteStatus(f);
        }

        List<List<String>> results = new ArrayList<>();

        for (List<String> path : paths) {
            if (isValid(path)) {results.add(path);}
        }

        return results;
    }

    private boolean isValid(List<String> path) {
        Folder current = root;

        for (String f : path) {
            current = current.map.get(f);

            if (current.del) {return false;}
        }

        return true;
    }

    private void updateDeleteStatus(Folder f) {
        if (f.list.size() > 0 && keys.get(f.key) > 1) {
            f.del = true;
            return;
        }

        for (Folder fold : f.list) {
            updateDeleteStatus(fold);
        }
    }

    private String generateKey(Folder fold) {
        StringBuilder sb = new StringBuilder();

        if (fold.list.size() == 0) {return sb.toString();}

        // sort to order matches
        Collections.sort(fold.list, Comparator.comparing(a -> a.name));

        for (Folder f : fold.list) {
            sb.append('(');
            sb.append(f.name + generateKey(f));
            sb.append(')');
        }

        String key = sb.toString();
        fold.key = key;
        keys.put(key, keys.getOrDefault(key, 0) + 1);

        return key;
    }

    private void addPath(List<String> path) {
        Folder current = root;

        for (String f : path) {
            if (!current.map.containsKey(f)) {
                Folder fold = new Folder(f);
                current.map.put(f, fold);
                current.list.add(fold);
            }

            current = current.map.get(f);
        }
    }
}

class Folder {
    String              name;
    Map<String, Folder> map;
    List<Folder>        list;
    String              key;
    boolean             del;

    Folder(String name) {
        this.name = name;
        map = new HashMap<>();
        list = new ArrayList<>();
        key = "";
        del = false;
    }

}