package study.erik.algorithm.job.zijie.simulation06;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.util.TreeNode;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/8 17:14
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_3 {

    @LetCodeCommit(title = "")
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            String left = serialize(root.left);
            String right = serialize(root.right);
            return String.format("%s(%s)(%s)", root.val, left, right);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {

            if (data.length() == 0) {
                return null;
            }
            int l = data.length();
            int i = 0;
            while (i < l && data.charAt(i) != '(') {
                i++;
            }
            int v = Integer.valueOf(data.substring(0, i));
            TreeNode root = new TreeNode(v);
            int j = findPair(data, i);
            root.left = deserialize(data.substring(i+1, j));

            i = j + 1;
            j = findPair(data, i);
            root.right = deserialize(data.substring(i+1, j));

            return root;
        }

        public int findPair(String s, int i){
            //不需要用栈，就能找到第一个括号匹配。
            int num = 0;
            int l = s.length();
            while (i < l) {
                char c = s.charAt(i);
                if(c == '('){
                    num++;
                } else if (c == ')') {
                    num--;
                    if(num == 0){
                        return i;
                    }
                }
                i++;
            }
            return -1;
        }
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {TreeNode.buildTree("[1,2,3,null,null,4,5]")},
        });
    }

    @Parameterized.Parameter
    public TreeNode root;

    @Test
    public void test() {
        Codec codec = new Codec();
        String serialize = codec.serialize(root);
        TreeNode deserialize = codec.deserialize(serialize);
        System.out.println(deserialize);
    }

}
