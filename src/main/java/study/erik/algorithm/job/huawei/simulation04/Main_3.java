package study.erik.algorithm.job.huawei.simulation04;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;

import java.util.*;

public class Main_3 {

    public int[][] multiSearch(String big, String[] smalls) {
        TireTree tree = new TireTree();
        for (int i = 0; i < smalls.length; i++) {
            tree.insert(smalls[i], i);
        }
        int sL = smalls.length;
        List<Integer>[] listArray = new List[sL];
        for (int i = 0; i < big.length(); i++) {
            List<Integer> list = tree.lookup(big.substring(i));
            for (Integer index : list) {
                if (listArray[index] == null) {
                    listArray[index] = new ArrayList<>();
                }
                listArray[index].add(i);
            }
        }
        int[][] ret = new int[sL][];
        for (int i = 0; i < listArray.length; i++) {
            List<Integer> l = listArray[i];
            if(l == null || l.isEmpty()){
                ret[i] = new int[0];
                continue;
            }
            int[] arr = new int[l.size()];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = l.get(j);
            }
            ret[i] = arr;
        }
        return ret;
    }

    public static class TireNode {
        TireNode[] child = new TireNode[26];
        // >= 0 表示是叶子结点
        int index = -1;

    }

    public static class TireTree {
        TireNode root = new TireNode();

        public void insert(String s, int indexStr) {
            int l = s.length();
            TireNode r = this.root;
            for (int i = 0; i < l; i++) {
                int idx = s.charAt(i) - 'a';
                if (r.child[idx] == null) {
                    r.child[idx] = new TireNode();
                }
                r = r.child[idx];
                if (i == l - 1) {
                    r.index = indexStr;
                }
            }
        }

        public List<Integer> lookup(String s) {
            int l = s.length();
            TireNode r = this.root;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < l; i++) {
                int idx = s.charAt(i) - 'a';
                if (r.child[idx] == null) {
                    return list;
                }
                r = r.child[idx];
                if (r.index >= 0) {
                    list.add(r.index);
                }
            }
            return list;
        }
    }

    @Test
    public void test() {
        String big = "mississippi";
        String[] smalls = new String[]{"is", "ppi", "hi", "sis", "i", "ssippi"};
        int[][] ints = multiSearch(big, smalls);
        System.out.println(Arrays.deepToString(ints));
    }

}
