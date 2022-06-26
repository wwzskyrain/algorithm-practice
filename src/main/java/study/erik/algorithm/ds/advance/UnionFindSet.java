package study.erik.algorithm.ds.advance;

/**
 * @author erik.wang
 * @date 2020-08-21 21:58
 */
public class UnionFindSet {

    private int[] parent;
    private int[] height;

    public UnionFindSet(int size) {
        parent = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        height = new int[size];
    }

    /**
     * 找到祖先结点
     * 1.这是迭代的方式
     * 2.在迭代的过程，给这篇森林"平坦化"
     *
     * @return
     */
    public int findWithIteration(int n) {
        while (parent[n] != n) {
            parent[n] = parent[parent[n]];
            n = parent[n];
        }
        return n;
    }

    /**
     * 1.这是'递归方式'
     * 2.在递归的过程，给这篇森林"平坦化"
     *
     * @param n
     * @return
     */
    public int findWithRecursion(int n) {
        if (n != parent[n]) {
            parent[n] = findWithRecursion(parent[n]);
        }
        return parent[n];
    }

    /**
     * 合并两簇， 应该保证，x和y确实不在一个簇中
     *
     * @param x
     * @param y
     */
    public void union(int x, int y) {

        int px = findWithRecursion(x);
        int py = findWithIteration(y);

        if (px == py) {
            return;
        }
        // height 只需要在根节点维护
        if (height[px] == height[py]) {
            height[px]++;
            parent[px] = py;
        } else if (height[px] < height[py]) {
            parent[px] = py;
        } else {
            parent[py] = px;
        }
    }

}
