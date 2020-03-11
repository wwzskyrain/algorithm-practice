package study.erik.algorithm.leetcode.backtracking;

import org.junit.Test;

import java.util.*;

/**
 * @author erik.wang
 * @date 2020-03-09 17:48
 * @description
 */
public class WordSearchII {


    public List<String> findWords(char[][] board, String[] words) {
        return solution1(board, words);
    }

    public class Node {
        public Character value;
        public boolean isWord = false;
        private Map<Character, Node> children = new HashMap<>();

        public Node(char value) {
            this.value = value;
        }


        public Node() {
        }

        public void insert(String word) {
            Node currentNode = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                Node child = currentNode.children.get(c);

                if (child == null) {
                    child = new Node(c);
                    currentNode.children.put(c, child);
                }
                currentNode = child;
                if (i == word.length() - 1) {
                    currentNode.isWord = true;
                }

            }
        }
    }

    public List<String> solution1(char[][] board, String[] words) {
        Node root = new Node();
        for (String word : words) {
            root.insert(word);
        }
        Set<String> results = new HashSet<>();
        byte[][] visited = new byte[board.length][];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = new byte[board[i].length];
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(results, board, visited, root, new LinkedList<>(), i, j);
            }
        }
        return new ArrayList<>(results);
    }

    /**
     * 改着改着就改成了dfs，而且是先序遍历
     * @param results
     * @param board
     * @param visited
     * @param words
     * @param stack
     * @param x
     * @param y
     */
    public void dfs(Set<String> results, char[][] board, byte[][] visited, Node words, Deque<Character> stack, int x, int y) {
        Node child = words.children.get(board[x][y]);
        if (child == null) {
            return;
        }
// 先序遍历
        stack.push(board[x][y]);
        visited[x][y] = 1;
        byte[][] direction = new byte[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        if (child.isWord) {
            Iterator<Character> iterator = stack.descendingIterator();
            StringBuilder stringBuilder = new StringBuilder();
            while (iterator.hasNext()) {
                stringBuilder.append(iterator.next().charValue());
            }
            results.add(stringBuilder.toString());
        }
        for (byte[] dir : direction) {
            int X = x + dir[0];
            int Y = y + dir[1];
            if (inBoard(X, Y, board) && visited[X][Y] == 0) {
                dfs(results, board, visited, child, stack, X, Y);
            }
        }
        stack.pop();
        visited[x][y] = 0;
    }

    private boolean inBoard(int x, int y, char[][] board) {
        return x >= 0 && y >= 0 && x < board.length && y < board[x].length;
    }

    @Test
    public void test_solution() {
        char[][] board = {{'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}};
        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        System.out.println(solution1(board, words));

        char[][] board1 = {{'a', 'a'}};

        String[] words1 = new String[]{"a"};
        System.out.println(solution1(board1, words1));
    }

}
