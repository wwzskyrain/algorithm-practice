package study.erik.algorithm.leetcode.hard.dp;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.grid.ImageOverlap;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期：2023/9/26 ，时间：10:23
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Stickers_to_Spell_Word {

    @LetCodeCommit(title = "691. Stickers to Spell Word",
            diff = "hard",
            selfRemark = "这个题目一看就适合回溯。" +
                    "但是我从题解列表中，首先看了这个dp的解法。" +
                    "dp的解法，AC之后的效率并不高。但是这个解法是很有意思的，我们来理解一下。" +
                    "在理解之前需要对题意特殊说明一点，那就是在挑选了一个stick之后，其字符在拼凑出target的任务中，" +
                    "能用仅用的。比如说see对于target=receive，see中的两个e要用都用，不能说我只用一个e。同时还需要说明，" +
                    "也可以不用完，比如target中就之后一个e，而这是see是可以完成拼凑这一个e的任务的。" +
                    "总之，不用考虑因为倍数被卡的问题。所以，能用尽用。" +
                    "OK，开始说这个dp解法：" +
                    "dp解法的思路就是不断的压缩target，也就是子问题。" +
                    "而从问题到子问题，则很自然就是把target减去一个sticker。" +
                    "好，进一步的问题来了，减去哪一个sticker呢？根据题设，当然是每一个都要减去试一试啦。" +
                    "OK，介绍完毕。" +
                    "还有一种回溯解法，见solution2")
    public int minStickers(String[] stickers, String target) {
        int l = stickers.length;
        int[][] stick = new int[l][26];
        for (int i = 0; i < l; i++) {
            String sticker = stickers[i];
            for (int j = 0; j < sticker.length(); j++) {
                stick[i][sticker.charAt(j) - 'a']++;
            }
        }
        Map<String, Integer> memorandum = new HashMap<>();
        memorandum.put("", 0);
        return dpSearchWithMem(stick, target, memorandum);
    }

    public int dpSearchWithMem(int[][] stickers, String target, Map<String, Integer> memorandum) {
        if (memorandum.containsKey(target)) {
            return memorandum.get(target);
        }
        int[] tar = new int[26];
        for (int i = 0; i < target.length(); i++) {
            tar[target.charAt(i) - 'a']++;
        }
        int ans = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            //这有一个优化点：如果当前sticker不满足target的第一个字符要求，就算了。
            //其实不太明白为何能优化。当然，至少很清楚这个优化不会漏掉最优解
            if (sticker[target.charAt(0) - 'a'] == 0) continue;
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (tar[i] == 0) {
                    continue;
                }
                for (int j = 0; j < Math.max(0, tar[i] - sticker[i]); j++) {
                    sb.append((char) (i + 'a'));
                }
            }
            if (sb.toString().equals(target)) {
                //如果当前sticker没有对target问题空间缩小you任何帮助，直接跳过。
                continue;
            }
            int tem = dpSearchWithMem(stickers, sb.toString(), memorandum);
            if (tem != -1) {
                ans = Math.min(ans, tem + 1);
            }
        }
        ans = ans == Integer.MAX_VALUE ? -1 : ans;
        memorandum.put(target, ans);
        return ans;
    }


    private int cnt = Integer.MAX_VALUE;

    /**
     * 这个回溯法是很容易想到的方向，但是写法上就各有千秋了，所以copy了这个写法
     *
     * @param stickers
     * @param target
     * @return
     */
    public int minStickersSolution2(String[] stickers, String target) {
        if (target == null) return -1;
        if (target.length() == 0) return 0;
        if (stickers == null || stickers.length == 0) return -1;

        int m = stickers.length;
        int[][] countMap;
        countMap = new int[m][26];

        for (int i = 0; i < stickers.length; i++) {
            String s = stickers[i];
            for (char c : s.toCharArray()) {
                countMap[i][c - 'a']++;
            }
        }
        count(0, 0, new int[26], target, stickers, countMap);
        return cnt == Integer.MAX_VALUE ? -1 : cnt;
    }

    private void count(int curCnt, int pos, int[] charAvailable, String target, String[] stickers, int[][] countMap) {
        if (curCnt >= cnt) return; //【重要】prune the search, when curCnt will be larger then cnt...
        //important step... other wise will get TLE..
        int m = stickers.length, n = target.length();
        if (pos == n) {
            cnt = Math.min(cnt, curCnt);
            return;
        }

        //这是这个写法的精髓，当然也是体现回溯的地方
        //主线是顺着pos走的——逐个字符的去检查target。而charAvailable则是当前花费的sticker的积累，这也是我们回溯的主体。
        //当charAvailable中有pos指向的字符，则pos就向前，即举例目标进了一步。但是ans不会加一。
        //同时注意这里有一个回溯点。
        char c = target.charAt(pos);
        if (charAvailable[c - 'a'] > 0) {
            charAvailable[c - 'a']--;
            count(curCnt, pos + 1, charAvailable, target, stickers, countMap);
            charAvailable[c - 'a']++;
        } else {
            for (int i = 0; i < m; i++) {
                //逐个把所有的sticker放到`charAvailable`中
                if (countMap[i][c - 'a'] == 0) continue;
                for (int j = 0; j < 26; j++) {
                    //一股脑，把当前的sticker放到charAvailable
                    charAvailable[j] += countMap[i][j];
                }
                //这时候才会加一，即使用了一个sticker；当然具体使用情况是if分支中。
                count(curCnt + 1, pos, charAvailable, target, stickers, countMap);
                //注意，这里是第二个回溯点。
                for (int j = 0; j < 26; j++) {
                    charAvailable[j] -= countMap[i][j];
                }
            }
        }
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {3, new String[]{"with", "example", "science"}, "thehat"},
                {-1, new String[]{"notice", "possible"}, "basicbasic"},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public String[] stickers;
    @Parameterized.Parameter(2)
    public String target;

    @Test
    public void test() {
        Assert.assertEquals(expect, minStickers(stickers, target));
    }

}
