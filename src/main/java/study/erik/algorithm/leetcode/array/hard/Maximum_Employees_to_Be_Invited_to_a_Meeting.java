package study.erik.algorithm.leetcode.array.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/1 22:10
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Maximum_Employees_to_Be_Invited_to_a_Meeting {

    @LetCodeCommit(title = "2127. Maximum Employees to Be Invited to a Meeting",
            selfRemark = "分析差一种情况。" +
                    "当环的大小大于2时，那这个环的人都可以参加。" +
                    "当环的大小等于2时，那么这两个相爱的人，都可以被其他人喜欢，而其他人还可以被其他人喜欢，" +
                    "所以可以拉出来两条入度链，这时候可以参会的就是这两条链上的人加上这两个相爱的人。" +
                    "我分析出来一条链，没有想到两条链呢。" +
                    "这有点像狗血的爱情剧，居中之后男女主角相爱，而他俩都分别被男二女二新欢，而男二又有可能被女三喜欢." +
                    "错错错，所有的这样的链子，算一个总和。")
    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] deg = new int[n]; // 入度
        for (int num : favorite) {
            deg[num]++;
        }
        List<Integer>[] reverseGraph = new List[n]; // 反图
        Arrays.setAll(reverseGraph, e -> new ArrayList<>());
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < deg.length; i++) {
            if (deg[i] == 0) {
                q.add(i);//入度为0的点
            }
        }
        while (!q.isEmpty()) { // 剩下的点都是有入度的，也就是说都在环上。
            int x = q.poll();
            int y = favorite[x];
            reverseGraph[y].add(x); //构建反向图，并不是构造所有节点的反向图，不包括环
            if (--deg[y] == 0) {
                q.add(y);
            }
        }
        int maxRingSize = 0;
        int sumChainSize = 0;
        for (int i = 0; i < n; i++) {
            if (deg[i] == 0) {
                continue;
            }
            deg[i] = 0; // // 将基环上的点的入度标记为 0，避免重复访问
            int ringSize = 1; // 基环长度
            for (int x = favorite[i]; x != i; x = favorite[x]) {
                deg[x] = 0;// 将基环上的点的入度标记为 0，避免重复访问
                ringSize++;
            }
            if (ringSize == 2) {
                sumChainSize += dfs(i, reverseGraph) + dfs(favorite[i], reverseGraph);
            } else {
                maxRingSize = Math.max(maxRingSize, ringSize);
            }
        }
        return Math.max(maxRingSize, sumChainSize);
    }

    // 直接dfs求深度，不需要bfs了；
    public int dfs(int x, List<Integer>[] rg) {
        int maxDepth = 1;
        for (Integer son : rg[x]) {
            maxDepth = Math.max(maxDepth, dfs(son, rg) + 1);
        }
        return maxDepth;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{

                {6, ArrayUtils.buildArray("[1,0,0,2,1,4,7,8,9,6,7,10,8]")},
                {4, ArrayUtils.buildArray("[1,2,3,0,1,4,2]")},
                {3, ArrayUtils.buildArray("[2,2,1,2]")},
                {3, ArrayUtils.buildArray("[1,2,0]")},
                {4, ArrayUtils.buildArray("[3,0,1,4,1]")},

        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] favorite;

    @Test
    public void test() {
        Assert.assertEquals(expect, maximumInvitations(favorite));
    }

}
