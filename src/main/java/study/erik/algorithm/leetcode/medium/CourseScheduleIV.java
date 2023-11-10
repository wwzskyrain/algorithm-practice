package study.erik.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleIV {

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        return solution1(numCourses, prerequisites, queries);
//        return solution2(numCourses, prerequisites, queries);
    }

    //广度优先遍历 + 拓扑排序
    public List<Boolean> solution1(int numCourses, int[][] prerequisites, int[][] queries) {
        //1.用list数组来保存图
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        //2.indegree 记录入度
        int[] indegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            indegree[pre[1]]++;
            graph[pre[0]].add(pre[1]);
        }
        //3.构造依赖关系，包括间接依赖
        boolean[][] isPre = new boolean[numCourses][numCourses];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            Integer cur = q.poll();
            List<Integer> children = graph[cur];
            for (Integer child : children) {
                isPre[cur][child] = true;
                for (int i = 0; i < numCourses; i++) {
                    //注意这里的方向：child依赖poll，poll依赖i
                    isPre[i][child] = isPre[i][child] | isPre[i][cur];
                }
                if (--indegree[child] == 0) {
                    q.offer(child);
                }
            }
        }
        List<Boolean> ret = new ArrayList<>();
        for (int[] query : queries) {
            ret.add(isPre[query[0]][query[1]]);
        }
        return ret;
    }

    //深度优先遍历 + 拓扑排序
    public List<Boolean> solution2(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Integer>[] g = new List[numCourses];
        for (int i = 0; i < g.length; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] pre : prerequisites) {
            g[pre[0]].add(pre[1]);
        }
        boolean[] visited = new boolean[numCourses];
        boolean[][] isPre = new boolean[numCourses][numCourses];
        for (int i = 0; i < numCourses; i++) {
            //注意可能是多个图，所以要用遍历，而且不需要从入度为0的点开始，所以这种写法更高级一些
            dfs(g, i, visited, isPre);
        }
        List<Boolean> ret = new ArrayList<>();
        for (int[] query : queries) {
            ret.add(isPre[query[0]][query[1]]);
        }
        return ret;
    }

    //dfs的方式处理cur的被依赖关系，包括间接被依赖关系。
    public void dfs(List<Integer>[] g, int cur, boolean[] visited, boolean[][] isPre) {
        if (visited[cur]) {
            return;
        }
        visited[cur] = true;
        List<Integer> neighbors = g[cur];
        for (Integer neighbor : neighbors) {
            dfs(g, neighbor, visited, isPre);
            isPre[cur][neighbor] = true;
            for (int i = 0; i < g.length; i++) {
                //注意这里的方向：因为neighbor已经访问了，所以那些对于neighbor的间接依赖已经转好好了。
                isPre[cur][i] = isPre[cur][i] | isPre[neighbor][i];
            }
        }
    }


}
