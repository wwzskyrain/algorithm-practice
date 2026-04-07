package study.erik.algorithm.nowcoder.huawei.medium;

import java.util.*;

//HJ133 隐匿社交网络
//这个题目虽然标题是并查集，但是真的用并查集的话，会超时；因为数据太多了。
//需要用特定的思路去优化，比如不用数组的形式表是group，比维护group只计数。
//下面的算法的特点就是
//1. 用一个long来表示一个group（天然编号）
//2. 只维护组内的成员个数，而不维护具体是哪些成员（这一点就很不想并查集中的parent数组）
//https://www.nowcoder.com/practice/2870f9db6aeb4eb08fbd6460397f9bf4?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D3%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37
public class InvisibleSocialNet {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int groups = in.nextInt();
        while (groups > 0) {
            int n = in.nextInt();
            long[] wight = new long[n];
            for (int i = 0; i < n; i++) {
                wight[i] = in.nextLong();
            }
            groups--;
            System.out.println(getMaxGroupSize(wight));
        }
    }

    public static int getMaxGroupSize(long[] weight) {
        // key就是group编号。value就是组成员数
        Map<Long, Integer> groupMap = new HashMap<>();
        for (long wight : weight) {
            int curGroupSize = 1;
            long newGroup = wight;
            Long[] groupArr = groupMap.keySet().toArray(new Long[0]);
            for (Long group : groupArr) {
                if ((group & wight) != 0) {
                    newGroup |= group; //合并成一个新组————位运算
                    groupMap.remove(group); // 删除旧组
                    curGroupSize += groupMap.get(group); //计算新组的成员数
                }
            }
            // 上面这个for循环，通过当前wight就串联了一个出来了一个新组newGroup，并消灭了一下旧组，
            // 最后把新组放到map中
            groupMap.put(newGroup, curGroupSize);
        }
        int max = 1;
        for (Integer size : groupMap.values()) {
            max = Math.max(max, size);
        }
        return max;
    }

}
