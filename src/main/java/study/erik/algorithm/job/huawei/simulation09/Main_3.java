package study.erik.algorithm.job.huawei.simulation09;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/8 11:08
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_3 {

    @LetCodeCommit(title = "LCR 033. 字母异位词分组")
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> retMap = new HashMap<>();
        for (String str : strs) {
            String arrange = arrange(str);
            List<String> list = retMap.getOrDefault(arrange, new ArrayList<>());
            list.add(str);
            retMap.put(arrange, list);
        }
        return new ArrayList<>(retMap.values());
    }

    public String arrange(String s){
        int[] chArr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            chArr[s.charAt(i) - 'a']++;
        }
        return Arrays.toString(chArr);
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}},
        });
    }

    @Parameterized.Parameter
    public String[] strs;
    @Test
    public void test() {
        System.out.println(groupAnagrams(strs));
    }

}
