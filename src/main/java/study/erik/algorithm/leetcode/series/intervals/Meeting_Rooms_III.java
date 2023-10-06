package study.erik.algorithm.leetcode.series.intervals;


import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/10/5 ，时间：09:34
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Meeting_Rooms_III {

    @LetCodeCommit(title = "2402. Meeting Rooms III",
            selfRemark = "这可是hard呀，竟然这么的没有难度。" +
                    "但是，我竟然也没有完全写对。哎呀，优点弱呀。" +
                    "差在哪儿了？我们也想到要在每个meeting的时候更新一下" +
                    "每个会议的状态，就差这儿了。但是我们之只用了一个treeMap，" +
                    "没办法遍历它的key，所以就卡主了。这时候用优先级队列最好了。")
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<Integer> idleRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            idleRooms.add(i);
        }
        int[] count = new int[n];
        PriorityQueue<Pair<Integer, Integer>> usingRooms =
                new PriorityQueue<>((p1, p2) -> !Objects.equals(p1.getKey(), p2.getKey()) ?
                        p1.getKey() - p2.getKey() : p1.getValue() - p2.getValue());
        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];
            while (!usingRooms.isEmpty() && usingRooms.peek().getKey() <= start) {
                //更新使用中的room的状态（到当前start时刻，这些room可能已经不再用了）
                Pair<Integer, Integer> poll = usingRooms.poll();
                idleRooms.add(poll.getValue());
            }
            if (!idleRooms.isEmpty()) {
                //优先用空闲的会议室。
                Integer idleRoom = idleRooms.poll();
                usingRooms.add(new Pair<>(end, idleRoom));
                count[idleRoom]++;
            } else {
                //用将来最早结束的会议室。
                Pair<Integer, Integer> poll = usingRooms.poll();
                usingRooms.add(new Pair<>(poll.getKey() + (end - start), poll.getValue()));
                count[poll.getValue()]++;
            }
        }
        int maxIndex = -1;
        int maxRoom = 0;
        for (int i = 0; i < n; i++) {
            if (count[i] > maxRoom) {
                maxRoom = count[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {0, 2, ArrayUtils.buildArray2Dimension("[[0,10],[1,5],[2,7],[3,4]]")},
                {1, 3, ArrayUtils.buildArray2Dimension("[[1,20],[2,10],[3,5],[4,9],[6,8]]")},
                {0, 4, ArrayUtils.buildArray2Dimension("[[18,19],[3,12],[17,19],[2,13],[7,10]]")}
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int n;
    @Parameterized.Parameter(2)
    public int[][] meetings;

    @Test
    public void test() {
        Assert.assertEquals(expect, mostBooked(n, meetings));
    }

}
