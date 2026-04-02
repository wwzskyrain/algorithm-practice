package study.huawei.medium;

import java.util.*;

//HJ48 从单向链表中删除指定值的节点
//非常简单，就是一般的链表操作
//https://www.nowcoder.com/practice/f96cd47e812842269058d483a11ced4f?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
public class DeleteKNodeInLink {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] numStrArr = in.nextLine().split(" ");
        int l = numStrArr.length;
        int headValue = Integer.parseInt(numStrArr[1]);
        LineNode header = new LineNode();
        header.v = headValue;
        // 可以快速找到目标节点
        Map<Integer, LineNode> v2NodeMap = new HashMap<>();
        v2NodeMap.put(header.v, header);
        int k = Integer.parseInt(numStrArr[l - 1]);
        List<Integer> nodes = new ArrayList<>();
        for (int i = 2; i < numStrArr.length - 1; i += 2) {
            int a = Integer.parseInt(numStrArr[i]);
            int b = Integer.parseInt(numStrArr[i + 1]);
            // (b -> a)
            LineNode node = v2NodeMap.get(b);
            LineNode aNode = new LineNode(a);
            // 插入
            aNode.next = node.next;
            node.next = aNode;
            v2NodeMap.put(a, aNode);

        }
        LineNode p = header;
        while (p != null) {
            if (p.v != k) {
                nodes.add(p.v);
            }
            p = p.next;
        }
        String[] ret = new String[nodes.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = nodes.get(i).toString();
        }
        System.out.println(String.join(" ", ret));
    }

    public static class LineNode {
        int v;
        LineNode next;

        public LineNode() {
        }

        public LineNode(int v) {
            this.v = v;
        }
    }
}
