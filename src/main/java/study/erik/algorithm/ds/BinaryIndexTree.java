package study.erik.algorithm.ds;

/**
 * @author erik.wang
 * @date 2020-05-05 12:04
 */
public class BinaryIndexTree {

    private int[] tree;

    public BinaryIndexTree(int[] tree) {
        this.tree = tree;
    }

    public int getSum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += tree[x];
            x -= lowBit(x);
        }
        return sum;
    }

    public void add(int index, int value) {
        while (index <= tree.length) {
            tree[index] += value;
            index += lowBit(index);
        }
    }

    private int lowBit(int x) {
        return x & (-x);
    }

    public static void main(String[] args) {

        int n = 10;
        BinaryIndexTree bit = new BinaryIndexTree(new int[n]);
        for (int i = 1; i < n - 1; i++) {
            bit.add(i, i);
        }
        for (int i = 1; i < n - 1; i++) {
            System.out.println(bit.getSum(i));
        }


    }
}
