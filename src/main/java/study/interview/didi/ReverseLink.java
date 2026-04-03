package study.interview.didi;


// didi一面：翻转link
public class ReverseLink {

    public static void main(String[] args) {
        // 翻转
        String ret = reverse("1->2->3->null");
        System.out.println(ret);
    }

    public static String reverse(String s) {
        //输入： 1->2->3->null
        //输出： 3->2->1->null
        String[] link = s.split("->");
        LinkNode header = new LinkNode();
        LinkNode p = header;
        for (int i = 0; i < link.length - 1; i++) {
            String vStr = link[i];
            int value = Integer.parseInt(vStr);
            LinkNode n = new LinkNode(value);
            p.next = n;
            p = p.next;
        }
        p = header.next;
        LinkNode header2 = new LinkNode();
        while (p != null) {
            LinkNode pp = p.next;
            p.next = header2.next;
            header2.next = p;
            p = pp;
        }
        return header2.toString();
    }

    public static class LinkNode {
        public int value;
        public LinkNode next;

        public LinkNode() {
        }

        public LinkNode(int value) {
            this.value = value;
        }

        public String toString() {
            LinkNode p = this.next;
            StringBuffer sb = new StringBuffer();
            while (p != null) {
                sb.append(p.value).append("->");
                p = p.next;
            }
            sb.append("null");
            return sb.toString();
        }

    }

}
