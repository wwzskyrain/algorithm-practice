package study.erik.algorithm.leetcode.huawei.medium;

import java.util.Scanner;

// HJ17 坐标移动
// https://www.nowcoder.com/practice/119bcca3befb405fbe58abe9c532eb29?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=
// 完全没有技术含量，就是字符串解析匹配
public class CoordMove {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] orders = in.nextLine().split(";");
        int x = 0, y = 0;
        for (String orderStr : orders) {
            Order order = buildOrder(orderStr);
            if (!order.validate) {
                continue;
            }
            switch (order.direction) {
                case 'A':
                    x = x - order.moveLength;
                    break;
                case 'D':
                    x = x + order.moveLength;
                    break;
                case 'W':
                    y = y + order.moveLength;
                    break;
                case 'S':
                    y = y - order.moveLength;
                    break;
            }
        }
        System.out.printf("%d,%d", x, y);

    }

    public static Order buildOrder(String order) {
        Order orderObj = new Order();
        if (order.length() > 3 || order.length() < 2) {
            orderObj.validate = false;
            return orderObj;
        }
        char direction = order.charAt(0);
        if (direction != 'A' && direction != 'W' && direction != 'S' && direction != 'D') {
            orderObj.validate = false;
            return orderObj;
        }
        orderObj.direction = direction;

        for (int i = 1; i < order.length(); i++) {
            char c = order.charAt(i);
            if (c < '0' || c > '9') {
                orderObj.validate = false;
                return orderObj;
            }
        }
        String sub = order.substring(1);
        if (sub.length() == 1) {
            orderObj.moveLength = sub.charAt(0) - '0';
            orderObj.validate = true;
            return orderObj;
        }
        if (order.charAt(1) == '0') {
            sub = order.substring(1);
        }
        orderObj.moveLength = Integer.parseInt(sub);
        orderObj.validate = true;
        return orderObj;
    }

    public static class Order {
        public char direction;
        public int moveLength;
        public boolean validate;
    }

}
