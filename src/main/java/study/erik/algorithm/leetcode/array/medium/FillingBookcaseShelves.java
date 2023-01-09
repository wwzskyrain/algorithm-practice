/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : FillingBookcaseShelves.java, v 0.1 2023年01月08日 19:42 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FillingBookcaseShelves {

    @LetCodeCommit(title = "1105. Filling Bookcase Shelves",
    selfRemark = "这个题目当然可以用dfs，但是容易超时。"
            + "所以使用了dp解法。")
    public int minHeightShelves(int[][] books, int shelfWidth) {
        //return dfs(books, shelfWidth, 0);
        return solutionWithDp(books, shelfWidth);
    }

    public int solutionWithDp(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] dp = new int[n + 1];
        // dp[i]以book[i-1]结尾的最优值
        dp[0] = 0;
        for (int i = 1; i <= books.length; ++i) {
            int width = books[i - 1][0];
            int height = books[i - 1][1];
            dp[i] = dp[i - 1] + height;
            //把左边临近的book依次拉过来到，拉倒新的一行；注意，这里有可能呢把上一行的拉空，也就
            //是消灭了上一行，用新行完全替换了上一行。
            for (int j = i - 1; j > 0 && width + books[j - 1][0] <= shelfWidth; --j) {
                height = Math.max(height, books[j - 1][1]);
                width += books[j - 1][0];
                dp[i] = Math.min(dp[i], dp[j - 1] + height);
            }
        }
        return dp[books.length];
    }

    public int dfs(int[][] books, int shelfWidth, int curBook) {
        if (curBook >= books.length) {
            return 0;
        }
        int curHigh = books[curBook][1];
        if (curBook == books.length - 1) {
            return curHigh;
        }

        int levelHigh = curHigh;
        int levelWidth = books[curBook][0];
        int leftH = dfs(books, shelfWidth, curBook + 1);
        int totalHigh = levelHigh + leftH;
        for (int i = curBook + 1; i < books.length; i++) {
            int iWith = books[i][0];
            int iHigh = books[i][1];
            if (iWith + levelWidth > shelfWidth) {
                break;
            }
            levelWidth += iWith;
            levelHigh = Math.max(levelHigh, iHigh);
            leftH = dfs(books, shelfWidth, i + 1);
            totalHigh = Math.min(totalHigh, levelHigh + leftH);
        }
        return totalHigh;
    }

    @Parameter
    public int[][] books;
    @Parameter(1)
    public int     shelfWidth;
    @Parameter(2)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]]"), 4, 6},
                {ArrayUtils.buildArray2Dimension("[[1,3],[2,4],[3,2]]"), 6, 4},
                {ArrayUtils.buildArray2Dimension("[[7,3],[8,7],[2,7],[2,5]]"), 10, 15},
                {ArrayUtils.buildArray2Dimension("[[11,83],[170,4],[93,80],[155,163],[134,118],[75,14],[122,192],[123,154],[187,29],[160,64],[170,152],[113,179],[60,102],[28,187],[59,95],[187,97],[49,193],[67,126],[75,45],[130,160],[4,102],[116,171],[43,170],[96,188],[54,15],[167,183],[58,158],[59,55],[148,183],[89,95],[90,113],[51,49],[91,28],[172,103],[173,3],[131,78],[11,199],[77,200],[58,65],[77,30],[157,58],[18,194],[101,148],[22,197],[76,181],[21,176],[50,45],[80,174],[116,198],[138,9],[58,125],[163,102],[133,175],[21,39],[141,156],[34,185],[14,113],[11,34],[35,184],[16,132],[78,147],[85,170],[32,149],[46,94],[196,3],[155,90],[9,114],[117,119],[17,157],[94,178],[53,55],[103,142],[70,121],[9,141],[16,170],[92,137],[157,30],[94,82],[144,149],[128,160],[8,147],[153,198],[12,22],[140,68],[64,172],[86,63],[66,158],[23,15],[120,99],[27,165],[79,174],[46,19],[60,98],[160,172],[128,184],[63,172],[135,54],[40,4],[102,171],[29,125],[81,9],[111,197],[16,90],[22,150],[168,126],[187,61],[47,190],[54,110],[106,102],[55,47],[117,134],[33,107],[2,10],[18,62],[109,188],[113,37],[59,159],[120,175],[17,147],[112,195],[177,53],[148,173],[29,105],[196,32],[123,51],[29,19],[161,178],[148,2],[70,124],[126,9],[105,87],[41,121],[147,10],[78,167],[91,197],[22,98],[73,33],[148,194],[166,64],[33,138],[139,158],[160,19],[140,27],[103,109],[88,16],[99,181],[2,140],[50,188],[200,77],[73,84],[159,130],[115,199],[152,79],[1,172],[124,136],[117,138],[158,86],[193,150],[56,57],[150,133],[52,186],[21,145],[127,97],[108,110],[174,44],[199,169],[139,200],[66,48],[52,190],[27,86],[142,191],[191,79],[126,114],[125,100],[176,95],[104,79],[146,189],[144,78],[52,106],[74,74],[163,128],[34,181],[20,178],[15,107],[105,8],[66,142],[39,126],[95,59],[164,69],[138,18],[110,145],[128,200],[149,150],[149,93],[145,140],[90,170],[81,127],[57,151],[167,127],[95,89]]"), 200, 120}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minHeightShelves(books, shelfWidth));
    }

}