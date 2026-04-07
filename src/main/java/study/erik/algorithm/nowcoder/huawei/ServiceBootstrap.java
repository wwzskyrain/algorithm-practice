package study.erik.algorithm.nowcoder.huawei;

import java.util.*;

// 服务启动：https://www.nowcoder.com/discuss/602464154787532800?sourceSSR=search
//
public class ServiceBootstrap {
    //    A->B C->B 表示 A依赖B
    public static void main(String[] args) {
        Map<String, Integer> indegreeMap = new HashMap<>();
        Map<String, List<String>> nextNodeMap = new HashMap<>();

        Scanner in = new Scanner(System.in);
        String[] relations = in.nextLine().split(" ");
        for (String relation : relations) {
            String[] edge = relation.split("->");
            String to = edge[0];
            String from = edge[1];

            indegreeMap.putIfAbsent(from, 0);
            indegreeMap.putIfAbsent(to, 0);
            Integer inDegree = indegreeMap.get(to);
            indegreeMap.put(to, inDegree + 1);

            nextNodeMap.putIfAbsent(from, new ArrayList<>());
            List<String> nextNodes = nextNodeMap.get(from);
            nextNodes.add(to);
            nextNodeMap.put(from, nextNodes);

        }
        List<String> ret = new ArrayList<>();
        while (true) {
            List<String> temp = new ArrayList<>();
            indegreeMap.forEach((node, indegree) -> {
                // 入度为0的点，就要被访问了。
                if (indegree == 0) {
                    temp.add(node);
                }
            });
            if (temp.size() == 0) {
                //  没有入度为0的点了，遍历结束
                break;
            }
            temp.sort(String::compareTo);
            for (String node : temp) {
                ret.add(node);
                indegreeMap.remove(node);
                // 注意：这里为了防止空指针，get的时候要给默认值
                List<String> nextNodes = nextNodeMap.getOrDefault(node, new ArrayList<>());
                for (String nextNode : nextNodes) {
                    Integer indegree = indegreeMap.get(nextNode);
                    indegree = indegree - 1;
                    indegreeMap.put(nextNode, indegree);
                }
            }
        }
        System.out.println(String.join(" ", ret));
    }

}



