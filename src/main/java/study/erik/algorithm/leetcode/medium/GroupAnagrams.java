package study.erik.algorithm.leetcode.medium;

import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author erik.wang
 * @date 2020-08-12 13:08
 */
public class GroupAnagrams {

    @LetCodeCommit(no = 49, title = "Group Anagrams", time = 26, space = 49,
            selfRemark = "很简单，就是不高效；难道m类型的题目就是不限时间的题目吗？看了一下dis区，也是这个解法")
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> key2Count = new HashMap<>();
        byte[] count = new byte[26];
        for (String str : strs) {
            for (int i = 0; i < str.length(); i++) {
                count[str.charAt(i) - 'a']++;
            }
            String key = Arrays.toString(count);
            List<String> words = key2Count.get(key);
            if (words == null) {
                words = new LinkedList<>();
            }
            words.add(str);
            for (int i = 0; i < count.length; i++) {
                count[i] = 0;
            }
            key2Count.put(key, words);
        }
        return new LinkedList(key2Count.values());
    }

    @Test
    public void test_case_1() {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }


}


