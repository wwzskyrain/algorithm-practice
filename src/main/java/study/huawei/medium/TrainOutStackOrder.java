package study.huawei.medium;

import java.util.*;

//HJ77 火车进站
//https://www.nowcoder.com/practice/97ba57c35e9f4749826dc3befaeae109?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
//没想到这个题目这么南呢
public class TrainOutStackOrder {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String[] trains = in.nextLine().split(" ");
        Stack<String> stack = new Stack<>();
        List<List<String>> allPath = new ArrayList<>();
        backtrace(trains, 0, stack, new ArrayList<>(), allPath);
        List<String> allPathStr = new ArrayList<>();
        for (List<String> path : allPath) {
            allPathStr.add(String.join(" ", path));
        }
        allPathStr.sort((o1, o2) -> o1.compareTo(o2));
        for (String s : allPathStr) {
            System.out.println(s);
        }
    }

    public static void backtrace(String[] trains, int idx, Stack<String> stack, List<String> path, List<List<String>> allPath) {
        if (path.size() == trains.length) {
            allPath.add(new ArrayList<>(path));
            return;
        }
        // 还有火车没进栈的快进
        if (idx < trains.length) {
            stack.push(trains[idx]);
            backtrace(trains, idx + 1, stack, path, allPath);
            stack.pop(); // 回溯
        }
        // 已经进栈的，就要出栈。
        // 注意栈里的元素，不是上面代码来的，上面进栈的代码一进一出了；而是从上一代来的。
        // 回溯的时候，又进栈了，所以这个栈的元素就留给了下一代咯。
        if (!stack.isEmpty()) {
            // 出栈
            String popTrain = stack.pop();
            path.add(popTrain);
            backtrace(trains, idx, stack, path, allPath);
            // 再次回溯
            path.remove(path.size() - 1);
            stack.push(popTrain);
        }
    }
}


