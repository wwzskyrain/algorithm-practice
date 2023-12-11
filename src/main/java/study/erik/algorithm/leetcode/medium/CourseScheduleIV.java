package study.erik.algorithm.leetcode.medium;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleIV {

    @LetCodeCommit(title = "1462. Course Schedule IV",
            selfRemark = "这个题目看好几遍了。")
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        return solution1(numCourses, prerequisites, queries);
//        return solution2(numCourses, prerequisites, queries);
    }

    //广度优先遍历 + 拓扑排序
    //和深度优先遍历的本质区别在于：
    //1.广度优先遍历，从入度为0的开始，处理到cur时，i到cur的关系已经确定了（包括间接关系）。借着当前明确的cur和child的关系，所以可以延伸i到child的关系
    //2.深度优先遍历，不需从入度0开始，处理到cur时，cur到i的关系已经确定了（包括间接关系）。借着当前明确的cur和child的关系，所以可以延伸cur到i的的关系
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
                    //注意，这里可以理解为，i到cur的关系已经处理好了，以此为基础，可以处理i到child的关系了。这一点和dfs是本质区别。
                    //或者说，i能到cur，就能到child
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
    //1.不需要记录入度
    //2.理解dfs函数的定义是关键，这样才能理解dfs中的实现。
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
                //因为neighbor已经处理好了，所以neighbor能到的i，我cur也都能到。
                //也就是“cur到所有的i”可以通过neighbor过度一下。
                //或者说，neighbor能到i，那cur也能到i。
                isPre[cur][i] = isPre[cur][i] | isPre[neighbor][i];
            }
        }
    }


}
