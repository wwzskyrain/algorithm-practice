package study.erik.algorithm.leetcode.sliding.window;

import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-06-21 15:36
 * title  = Minimum Number of K Consecutive Bit Flips
 */
public class MinimumNumberofKConsecutiveBitFlips {


    @Test
    public void test_() {

    }


    /**
     * 思考：
     * 1.  这是个操作题还是个计算题？操作题的话，如何能找到全部操作，然后比较出每种操作的代价
     * 2.  计算题的话，如何求出最值呢？
     * 一下是大神的解法：
     * 思路如下：
     * 1.  按照贪心思想，从左往右执行
     * 2.  找到第一个0，flipp以它开头的K个元素，计数加一
     * 3.  重复第2步，直到最后一个K窗口
     * 但是这样的解法是最优吗？这样的解法在复杂度太大了吧
     * 1.是否最优？贪心算法的最优证明很难，这里我们也不考虑，直觉上是这样的
     * 2.复杂度：KN
     * 复杂度要降下来
     * 1.如实际解法，用isFlipped记录哪些位置被flipped了，并用一个变量flipped来记录当前节点是否已经被flipped。
     * 2.这个flipped大有来头，它表示的是自第一个以来，累积后，当前节点是否需要flipped，但是呢，当用它来参与是否要对
     * 当前元素进行flip的时候，又不能用全部累积的flipped，只能用当前元素之前的的K窗口之内的，所以就必须把K距离之外的所有flip减掉。
     * 3.那么如何运用flip状态和A[i]元数据来判断呢
     * 1.如果 flip=0，A[i] = 0，则需要反转flip
     * 2.如果 flip=1，A[i] = 1，则需要反转flip，因为当前的A[i]已经被反转成0了，所以需要反转一下
     * 3.补充，整个过程中A[i]都是不变的，只是用isFlipped数组来记录i元素是否进行的反转(实际上是反转i开头的K个元素)
     * 4.还有两个问题
     * 1.  变量flip如何累加
     * 答：flip ^= 1  ——异或大法
     * 2.  变量flip如何减去K之外的历史flip
     * 答：flip ^= isFlipped[i-k]  --每一次循环主次递减
     * 注意，isFlipped[i]不是flip在i处的累积值，当时的一个步长delta，所以一路减下来，就是减去了历史累积值
     *
     * 总结：
     *  1.  能理解这一切都是很不容易的(或许我以上的理解都是自我揣测)
     *  2.  不能扩展，因为只有10两种；其实也不用扩展，这题扩展起来也没意思
     * @param A
     * @param K
     * @return
     */
    public int minKBitFlips(int[] A, int K) {

        int n = A.length, flipped = 0, res = 0;
        int[] isFlipped = new int[n];
        for (int i = 0; i < A.length; ++i) {
            if (i >= K) {
                //减去delta——对于位的异或运算，加减都是异或——再次为异或着迷
                flipped ^= isFlipped[i - K];
            }
            if (flipped == A[i]) {
                //需要flip了
                if (i + K > A.length) {
                    return -1;
                }
                //记录flipped增量
                isFlipped[i] = 1;
                //flipped累加
                flipped ^= 1;
                res++;
            }
        }
        return res;

    }

}
