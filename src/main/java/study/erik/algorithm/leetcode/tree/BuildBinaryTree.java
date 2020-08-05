package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import study.erik.algorithm.leetcode.util.TreeNode;

/**
 * @author erik.wang
 * @date 2019/04/07
 **/
public class BuildBinaryTree {

    /**
     * title=Construct Binary Tree from Preorder and Inorder Traversal
     * 一气呵成，能被接收也挺不错的，虽然时间和空间都表现很差。
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder.length == 0) {
            return null;
        }
        int firstValue = preorder[0];

        int index = index(firstValue, inorder);
        int leftLength = index;
        int rightLength = inorder.length - index - 1;

        int[] leftPreOrder = new int[leftLength];
        int[] rightPreOrder = new int[rightLength];
        System.arraycopy(preorder, 1, leftPreOrder, 0, leftLength);
        System.arraycopy(preorder, 1 + leftLength, rightPreOrder, 0, rightLength);

        int[] leftInorder = new int[leftLength];
        int[] rightInorder = new int[rightLength];
        System.arraycopy(inorder, 0, leftInorder, 0, leftLength);
        System.arraycopy(inorder, index + 1, rightInorder, 0, rightLength);

        TreeNode leftRoot = buildTree(leftPreOrder, leftInorder);
        TreeNode rightRoot = buildTree(rightPreOrder, rightInorder);

        TreeNode root = new TreeNode(firstValue);
        root.left = leftRoot;
        root.right = rightRoot;

        return root;
    }


    @Test
    public void test_buildTreeI() {

        TreeNode root = buildTreeI(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
//        TreeNode root = buildTreeI(new int[]{1, 2}, new int[]{2, 1});
        System.out.println(root.val);

    }


    public TreeNode buildTreeI(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, inorder, 0, 0, inorder.length);
    }

    /**
     * 还是很慢，算了，不优化了。
     * @param preorder
     * @param inorder
     * @param preIndex
     * @param inStart
     * @param inLength
     * @return
     */
    public TreeNode buildTreeHelper(int[] preorder, int[] inorder, int preIndex, int inStart, int inLength) {

        if (inLength == 0) {
            return null;
        }

        if (inLength == 1) {
            return new TreeNode(preorder[preIndex]);
        }

        int rootValue = preorder[preIndex];
        int i = 0;//当前节点值在inOrder的相对索引
        while (i < inLength) {
            if (inorder[inStart + i] == rootValue) {
                break;
            }
            i++;
        }

        TreeNode leftRoot = buildTreeHelper(preorder, inorder, preIndex + 1, inStart, i);
        TreeNode rightRoot = buildTreeHelper(preorder, inorder, preIndex + i + 1, inStart + i + 1, inLength - i - 1);

        TreeNode root = new TreeNode(rootValue);
        root.left = leftRoot;
        root.right = rightRoot;
        return root;
    }

    public int index(int value, int[] data) {

        if (data.length == 0) {
            return -1;
        }

        for (int i = 0; i < data.length; i++) {
            if (data[i] == value) {
                return i;
            }
        }
        return -1;
    }

}
