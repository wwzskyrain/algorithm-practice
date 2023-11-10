package study.erik.algorithm.job.zijie.simulation07;

import java.util.*;

public class Main_2 {

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        boolean[] hasBefore = new boolean[numCourses]; //被依赖
        Map<Integer, Set<Integer>> map = new HashMap();
        for (int[] pre : prerequisites) {
            int before = pre[0];
            int after = pre[1];
            hasBefore[before] = true;
            Set<Integer> set = map.getOrDefault(after, new HashSet<Integer>());
            set.add(before);
            map.put(after, set);
        }
        for (int i = 0; i < hasBefore.length; i++) {
            if (!hasBefore[i]) {
                dfs(map, i);
            }
        }
        List<Boolean> ret = new ArrayList();
        for (int[] q : queries) {
            ret.add(map.getOrDefault(q[1], new HashSet()).contains(q[0]));
        }
        return ret;
    }

    public Set<Integer> dfs(Map<Integer, Set<Integer>> map, int i) {
        Set<Integer> needSet = map.get(i);
        if(needSet == null){
            return null;
        }
        Set<Integer> needSetCopy = new HashSet<>(needSet);
        for (Integer need : needSetCopy) {
            needSet.addAll(dfs(map, need));
        }
        return needSet;
    }
}
