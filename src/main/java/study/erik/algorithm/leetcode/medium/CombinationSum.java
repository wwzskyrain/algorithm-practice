package study.erik.algorithm.leetcode.medium;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * @author erik.wang
 * @date 2020-03-08 17:05
 * @description
 */
public class CombinationSum {

    private static final Logger logger = LoggerFactory.getLogger(CombinationSum.class);

    /**
     * title=Combination Sum
     * url=https://leetcode.com/problems/combination-sum/
     * 解法：dfs
     * 成绩：11% + 5% 太差了，哈哈，不过还是过了
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> candidatesList = IntStream.of(candidates).boxed().collect(Collectors.toList());
//        Collections.sort(candidatesList, Collections.reverseOrder());
        List<List<Integer>> results = new ArrayList<>();
        dfs(results, new LinkedList<>(), candidatesList, target, 0);
        return results;
    }

    /**
     *
     * @param results 保存所有结果(路径)
     * @param currentPath 当前搜索路径
     * @param candidates 数据
     * @param target 当前目标数
     * @param index 当前搜索结点的索引--用于防止出现
     */
    public void dfs(List<List<Integer>> results, Deque<Integer> currentPath, List<Integer> candidates, int target, int index) {

        if (target == 0) {
            results.add(new ArrayList<>(currentPath));
            return;
        }
        for (int i = index; i < candidates.size(); i++) {
            int candidate = candidates.get(i);
            if (target >= candidate) {
                currentPath.push(candidate);
                //注意 这里 （实参target-candidate）；
                //注意 i 作为实参
                dfs(results, currentPath, candidates, target - candidate, i);
                currentPath.pop();
            }

        }
    }

    @Test
    public void test() {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        logger.info("results={}", JSON.toJSONString(combinationSum(candidates, target)));
    }

}
