package study.erik.algorithm.leetcode.dp;

import org.junit.Test;

import java.util.*;

/**
 * @author erik.wang
 * @date 2020-03-14 22:50
 * @description
 */
public class WorkBreakII {

    /**
     * title = word break II
     * url = https://leetcode.com/problems/word-break-ii/submissions/
     * 难点分析：找出所有解，这类题目一般都要用递归了。
     * 这道题目做了一个上午，前后用了三种方法，所以可以好好的写点东西出来
     * 第一种方法，用一个stack来保存结果。在全局遍历时，没有保存子问题的解，导致大量重复计算，耗时严重，所以提交后超时。
     * 第二种方法，用一个map来保存子问题结果。然而，在递归时，是遍历字典来递归，还是遍历s来递归呢？于是就有了第三种解法
     * 第三种解法，和第二种解法的不同在于，第二种解法是按照字典来递归，第三种解法是按照s来递归。
     * 然后，这两种方法在本质上是一样的，其搜索空间也是一样的。
     * 但是，摘抄自网上讨论区答案的第二种解法，可以通过提交而不超时；第三种却超时。
     * 自己对比之后发现，第二种方法的代码有两处优化
     * 1.  wordDict用HashSet来组织
     * 2.  map的value用LinkedList来实现，因为这里只需要在尾部追加，所以用链表比较合适；然后，就不要使用size()了。还有一个好处，就是随着list的扩大，不用resize.
     * 3.  最重要：不要用map的value的empty标志来表示是否缓存了s的结果，直接用map.containKey来表示。
     * 4.  很遗憾，第三条并不是最重要的。超时的原因定位了，是list.size()的问题。
     * 5.  如果是ArrayList那么size()没问题呀，因为其复杂度是O(1)。所以就是有点奇怪呢。
     * 6.  为什么推翻3呢，因为看了一下HashMap的源码，containKey和get方法是一样的实现，所以才怀疑3的猜测是不对的。
     *
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        return solution2(s, wordDict);
    }


    public List<String> solution2(String s, List<String> wordDict) {
        return dfs2(s, new HashSet<>(wordDict), new HashMap<>());
    }

    /**
     *
     * @param s
     * @param wordDict
     * @param cache
     * @return
     */
    public List<String> dfs2(String s, Set<String> wordDict, Map<String, List<String>> cache) {

        List<String> list = cache.get(s);
        if (list != null) { //注意：重要！！！ 如果这里在用list.size()!=0判断一下的话，就超时了。所以，以后不要在用size()了。
            return list;
        }
//        if (cache.containsKey(s)) { //如果不用上面的list!=null来判断，用containsKey也可以的。
        //其实这里相对于上面的用法，上面是优的，因为containsKey和get其底层实现是一样的；
//            return cache.get(s);
//        }

        List<String> foundAllSentence = new ArrayList<>();
        if (wordDict.contains(s)) {
            foundAllSentence.add(s);
        }

        for (int i = 1; i < s.length(); i++) {
             //这里是按照s的子串来做递归遍历的，也可以按照字典来做，见dfs3
            String preSubString = s.substring(0, i);
            if (wordDict.contains(preSubString)) {
                List<String> sentences = dfs2(s.substring(i), wordDict, cache);
                for (String sentence : sentences) {
                    foundAllSentence.add(preSubString + " " + sentence);
                }
            }
        }

        cache.put(s, foundAllSentence);
        return foundAllSentence;
    }

    // DFS function returns an array including all substrings derived from s.
    List<String> dfs3(String s, Set<String> wordDict, HashMap<String, LinkedList<String>> map) {
        if (map.containsKey(s))
            return map.get(s);

        LinkedList<String> res = new LinkedList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            //这里用字典来做遍历
            if (s.startsWith(word)) {
                List<String> sublist = dfs3(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }

    public List<String> solution1(String s, List<String> wordDict) {
        List<String> allSentences = new ArrayList<>();
        Deque<String> stack = new LinkedList<>();
        dfs(allSentences, s, stack, wordDict);
        return allSentences;
    }

    /**
     *
     * @param allSentences
     * @param s
     * @param stack
     * @param wordDict
     */
    public void dfs(List<String> allSentences, String s, Deque<String> stack, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return;
        }

        if (wordDict.contains(s)) {
            stack.push(s);
            Iterator<String> iterator = stack.descendingIterator();
            StringBuilder stringBuilder = new StringBuilder();
            while (iterator.hasNext()) {
                stringBuilder.append(iterator.next()).append(" ");
            }
            String sentence = stringBuilder.toString();
            sentence = sentence.substring(0, sentence.length() - 1);
            allSentences.add(sentence);
            stack.pop();
        }

        for (int i = 0; i < s.length(); i++) {
            String preSubStr = s.substring(0, i + 1);
            if (wordDict.contains(preSubStr)) {
                stack.push(preSubStr);
                dfs(allSentences, s.substring(i + 1), stack, wordDict);
                stack.pop();
            }
        }
    }

    @Test
    public void test_solution() {

        String s3 = "aaaaaaaaaaaaaaaaaaaaaaaa";
        List<String> wordDict3 = Arrays.asList("a", "aa", "aaaaaaa", "aaaaaaaaaaaaa");
        System.out.println(wordBreak(s3, wordDict3));

        String s2 = "catsandog";
        List<String> wordDict2 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(wordBreak(s2, wordDict2));

        String s1 = "pineapplepenapple";
        List<String> wordDict1 = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(wordBreak(s1, wordDict1));

        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        System.out.println(wordBreak(s, wordDict));

    }


}
