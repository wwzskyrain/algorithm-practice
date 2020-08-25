package study.erik.algorithm.ds.advance;

/**
 * @author erik.wang
 * @date 2020-08-21 21:58
 */
public class UnionFindSet {

    private int size;
    private int[] parent;
    private int[] height;

    public UnionFindSet(int size) {
        this.size = size;
        parent = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        height = new int[size];
    }

    /**
     * 找到祖先结点
     *
     * @return
     */
    public int findWithIteration(int n) {
        int root = n;
        while (root != parent[root]) {
            root = parent[root];
        }
        int nn = n;
        while (parent[nn] != root) {
            int temp = parent[nn];
            parent[nn] = root;
            nn = temp;
        }
        return root;
    }

    public int findWithRecursion(int n) {
        if (n != parent[n]) {
            parent[n] = findWithIteration(parent[n]);
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
