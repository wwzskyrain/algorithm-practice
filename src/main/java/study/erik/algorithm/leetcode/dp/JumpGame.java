package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2019/05/30
 **/
public class JumpGame {

    /**
     * 不做总结，等于白做
     * 这个题目的描述很有意思，而dp的思路却很简单。
     * 题意是描述能不能跳过去，而不能跳过去的关键就是存在0点。但是dp的思路却不在这里。
     * 可以通过判断0点来加快'可以跳过去'测试case。但是dp的思路却不在这里。
     * dp思路：根据问题定义名词：好点、坏点。好点是能够跳过去的点，即能够满足题意的点。其他的为坏点。
     * 有了思路，写代码就简单了，一遍成型，加一处小修改，就成功；可是效率很低，13%，内存使用量甚少，少于99%。
     * 加速策略：
     * 1.   没有0就可以直接返回true
     * 2.   赋值canJump时由远及近而不是由近及远 + 加快到30%
     * 附原题链接 https://leetcode.com/problems/jump-game/
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {

        boolean[] canJump = new boolean[nums.length];
        int length = nums.length;
        canJump[length - 1] = true;

        for (int i = nums.length - 2; i >= 0; i--) {
            int span = nums[i];
            while (span > 0) {
                if (i + span < length && canJump[i + span]) {
                    canJump[i] = true;
                    break;
                }
                span--;
            }
        }
        return canJump[0];
    }

    @Test
    public void test_can_jump() {

        int[] array1 = new int[]{2, 3, 1, 1, 4};
        int[] array2 = new int[]{3, 2, 1, 0, 4};

        Assert.assertTrue(canJump(array1));
        Assert.assertFalse(canJump(array2));
    }

    public int jump(int[] nums) {

        int length = nums.length;
        int[] mixStep = new int[length];

        for (int i = 0; i < length - 1; i++) {
            mixStep[i] = Integer.MAX_VALUE;
        }

        mixStep[length - 1] = 0;

        for (int i = length - 2; i >= 0; i--) {

            int min = length;
            for (int j = 1; j <= nums[i] && j + i < length; j++) {
                min = Math.min(min, mixStep[j + i]);
            }
            mixStep[i] = min + 1;
        }

        return mixStep[0];
    }

    @Test
    public void test_jump() {
        int[] array1 = new int[]{2, 3, 1, 1, 4};
        int[] array2 = new int[]{2, 3, 0, 1, 4};
        Assert.assertEquals(2, jump(array1));
        Assert.assertEquals(2, jump(array2));
    }


}
