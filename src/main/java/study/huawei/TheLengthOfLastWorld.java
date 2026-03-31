package study.huawei;

import java.util.Scanner;

// 字符串最后一个单词的长度
public class TheLengthOfLastWorld {


    // 注意类名必须为 Main, 不要有任何 package xxx 信息

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        if (in.hasNextLine()) { // 注意 while 处理多个 case
            String line = in.nextLine();
            String[] words = line.split(" ");
            System.out.println(words[words.length - 1].length());
        } else {
            System.out.println("error: no input");
        }
    }


}
