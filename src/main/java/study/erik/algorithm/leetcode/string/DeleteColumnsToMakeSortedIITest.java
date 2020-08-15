package study.erik.algorithm.leetcode.string;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-07 16:55
 */
public class DeleteColumnsToMakeSortedIITest {


    @LetCodeCommit(no = 955, title = "Delete Columns to Make Sorted II", time = 100, timeMillisecond = 0, space = 68, diff = "m",
            selfRemark = "又是野路子，状态比较多，还是通过提交的错误case才完善的，不过计算框架是没问题的。",
            related = {"Task Scheduler", "Group the People Given the Group Size They Belong To", "Maximum Number of Events That Can Be Attended"},
            extend = "lee大神的代码运行了4ms，time=32，和我的相差不少呢；但是他也是O(mn)，不知道差在了哪里")
    public int minDeletionSize(String[] A) {

        // 两个交叉比较数组
        int[][] c = new int[2][A.length];
        int curIndex = 0;
        int count = 0;
        for (int j = 0; j < A[0].length(); j++) {
            boolean hasSameChar = false;
            boolean hasDisOrderChar = false;
            //1.先遍历一遍做检查
            for (int i = 1; i < A.length; i++) {
                if (c[curIndex][i] != 0) {
                    continue;
                }
                char ch = A[i].charAt(j);
                char preCh = A[i - 1].charAt(j);
                if (ch < preCh) {
                    hasDisOrderChar = true;
                    break;
                } else if (ch == preCh) {
                    hasSameChar = true;
                }
            }

            //2.在做具体的比较结果赋值
//          直接删除该列
            if (hasDisOrderChar) {
                count++;
//              count and next column
                continue;
            }
            //
            if (hasSameChar) {
                int nextIndex = (curIndex + 1) % 2;
                for (int i = 1; i < A.length; i++) {
                    if (c[curIndex][i] != 0) {
                        //这里要延续上一列的比较的值，主要是延续下来那些1值
                        c[nextIndex][i] = c[curIndex][i];
                        continue;
                    }
                    char ch = A[i].charAt(j);
                    char preCh = A[i - 1].charAt(j);
                    if (ch > preCh) {
                        c[nextIndex][i] = 1;
                    } else {
                        c[nextIndex][i] = 0;
                    }
                }
                curIndex = nextIndex;
                continue;
            }
            // no disorder char, and no same char, so it is ordered
            return count;
        }
        return count;
    }

    @Test
    public void test_case_1() {
        Assert.assertEquals(1, minDeletionSize(new String[]{"ca", "bb", "ac"}));
    }

    @Test
    public void test_case_2() {
        Assert.assertEquals(0, minDeletionSize(new String[]{"xc", "yb", "za"}));
    }

    @Test
    public void test_case_3() {
        Assert.assertEquals(3, minDeletionSize(new String[]{"zyx", "wvu", "tsr"}));
    }

    @Test
    public void test_case_4() {
        Assert.assertEquals(1, minDeletionSize(new String[]{"xga", "xfb", "yfa"}));
    }

    @Test
    public void test_case_5() {
        Assert.assertEquals(3, minDeletionSize(new String[]{"jqonkbi", "joeikeh", "lqolzdm"}));
    }

}
