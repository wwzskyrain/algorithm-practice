package study.erik.algorithm.classic.blance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class WeightRoundRobin {


    /**
     * 随机数实现的加权轮训
     * 这个是在没有技术含量，直接就是总权重内的随机数，然后来一个区间分布
     */
    public int random_weight_round_robin() {
        int[] weight = {10, 20, 50};
        int totalWeight = Arrays.stream(weight).sum();
        int offset = ThreadLocalRandom.current().nextInt(totalWeight);
        for (int i = 0; i < weight.length; i++) {
            offset -= weight[i];
            if (offset < 0) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test_() {
        int[] c = new int[3];
        IntStream.range(0, 8000).forEach(i -> {
            int idx = random_weight_round_robin();
            c[idx]++;
        });
        //差不多
        System.out.println(Arrays.toString(c));

        IntStream.range(0, 16).forEach(i -> System.out.println(random_weight_round_robin()));
    }


    /**
     * 平滑加权算法，nginx的算法。
     * 请看有效性和平滑性证明：https://tenfy.cn/2018/11/12/smooth-weighted-round-robin/#%E8%BD%AE%E8%AF%A2%E8%B0%83%E5%BA%A6
     */
    public List<Integer> smoothly_weight_round_robin(int[] weight, int n) {
        int total = Arrays.stream(weight).sum();
        List<Integer> select = new ArrayList<>();
        int[] cur = new int[weight.length];
        for (int i = 0; i < n; i++) {
            int maxIdx = 0;
            for (int j = 0; j < weight.length; j++) {
                //加上权重
                cur[j] += weight[j];
                if (cur[maxIdx] < cur[j]) {
                    //挑选本轮最大值
                    maxIdx = j;
                }
            }
            //选择最大的index
            select.add(maxIdx);
            //cur权重减去total。
            cur[maxIdx] -= total;
        }
        return select;
    }

    @Test
    public void test() {
        List<Integer> list = smoothly_weight_round_robin(new int[]{5, 1, 1}, 7);
        System.out.println(list);
    }
}
