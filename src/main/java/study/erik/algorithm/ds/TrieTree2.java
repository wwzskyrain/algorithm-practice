package study.erik.algorithm.ds;



/**
 * @author erik.wang
 * @date 2020-05-07 08:40
 */
public class TrieTree2 {

    private final static int INIT_SIZE = 100000;
    private final static int ALPHABET_SIZE = 26;

    /**
     * tree中存放下一个结点的位置(下标)
     */
    private int[][] tree = new int[INIT_SIZE][ALPHABET_SIZE];

    /**
     * 存放树节点对应的num
     */
    private int[] num = new int[INIT_SIZE];
    private int pos = 1;

    /**
     * 插入word到前缀tree中
     *
     * @param word
     */
    void insert(String word) {
        int p = 0;
        for (int i = 0; i < word.length(); i++) {
            int n = word.charAt(i) - 'a';
            if (tree[p][n] == 0) {
                tree[p][n] = pos++;
            }
            p = tree[p][n];
            num[p]++;
        }
    }

    /**
     * 找到以word为前缀的单词数量
     *
     * @param word
     * @return
     */
    int find(String word) {
        int p = 0;
        for (int i = 0; i < word.length(); i++) {
            int n = word.charAt(i) - 'a';
            if (tree[p][n] == 0) {
                return 0;
            }
            p = tree[p][n];
        }
        return num[p];
    }


}
