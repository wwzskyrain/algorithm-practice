package study.erik.algorithm.leetcode.weekly001;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 日期：2023/10/18 ，时间：10:33
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class LastVisitedIntegers {

    @LetCodeCommit(title = "")
    public List<Integer> lastVisitedIntegers(List<String> words) {
        List<Integer> nums = new ArrayList<>();
        List<Integer> ret = new ArrayList<>();
        int curNum = -1;
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if (word.equals("prev")) {
                if (curNum < 0) {
                    ret.add(-1);
                } else {
                    ret.add(nums.get(curNum--));
                }
            } else {
                Integer num = Integer.valueOf(word);
                nums.add(num);
                curNum = nums.size() - 1;
            }
        }
        return ret;
    }


}
