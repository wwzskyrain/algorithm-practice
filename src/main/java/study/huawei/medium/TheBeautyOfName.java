package study.huawei.medium;

import java.util.*;

//HJ45 名字的漂亮度
//https://www.nowcoder.com/practice/02cb8d3597cf416d9f6ae1b9ddc4fde3?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
public class TheBeautyOfName {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        List<String> names = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            names.add(in.nextLine());
        }
        for (String name : names) {
            System.out.println(CalBeauty(name));
        }
    }

    public static int CalBeauty(String name) {
        int[] charNum = new int[26];
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            charNum[c - 'a']++;
        }
        Arrays.sort(charNum);
        int beauty = 0;
        for (int i = 26; i > 0; i--) {
            int n = charNum[i - 1];
            if (n == 0) {
                //快速结束.
                break;
            }
            beauty += i * n;
        }
        return beauty;
    }

}
