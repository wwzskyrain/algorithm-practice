package study.erik.algorithm.nowcoder;

/**
 * @author erik.wang
 * @date 2019/06/24
 **/
public class BinaryTreeMirror {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /**
     * 递归实现非常简单。
     * todo 迭代实现呢？
     * @param root
     */
    public void Mirror(TreeNode root) {

        if(root == null){
            return;
        }

        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;


        Mirror(root.left);
        Mirror(root.right);

    }
}
