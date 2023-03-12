/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author yueyi
 * @version : RemoveSub_FoldersFromTheFilesystem.java, v 0.1 2023年03月12日 14:38 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class RemoveSub_FoldersFromTheFilesystem {

    @LetCodeCommit(title = "1233. Remove Sub-Folders from the Filesystem")
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        Set<String> set = new TreeSet<>();
        for (String f : folder) {
            int length = f.length();
            boolean isSub = false;
            for (int i = length - 1; i > 0; i--) {
                if (f.charAt(i) == '/') {
                    String pre = f.substring(0, i);
                    if (set.contains(pre)) {
                        isSub = true;
                        break;
                    }
                }
            }
            if (!isSub) {
                set.add(f);
            }
        }
        return new ArrayList<>(set);
    }

    @Parameter
    public String[] folder;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"}},
                {new String[] {"/a", "/a/b/c", "/a/b/d"}},
                {new String[] {"/a/b/c", "/a/b/ca", "/a/b/d"}},
        };
    }

    @Test
    public void test_() {
        System.out.println(removeSubfolders(folder));
    }

}