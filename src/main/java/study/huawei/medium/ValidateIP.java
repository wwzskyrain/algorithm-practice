package study.huawei.medium;

import java.util.Scanner;

//HJ90 合法IP
//https://www.nowcoder.com/practice/995b8a548827494699dc38c3e2a54ee9?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
public class ValidateIP {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] ipSubStr = in.nextLine().split("\\.");
        if (ipSubStr.length != 4) {
            System.out.println("NO");
            return;
        }
        int[] ipSubInt = new int[ipSubStr.length];
        boolean valid = true;
        for (int i = 0; i < ipSubStr.length; i++) {
            String ipSubs = ipSubStr[i];
            if (ipSubs.isEmpty()) {
                valid = false;
                continue;
            }
            if (ipSubs.length() > 1 && (ipSubs.charAt(0) < '1' || ipSubs.charAt(0) > '9')) {
                valid = false;
                continue;
            }
            try {
                ipSubInt[i] = Integer.parseInt(ipSubs);
            } catch (Exception e) {
                valid = false;
                break;
            }
            if (ipSubInt[i] > 255) {
                valid = false;
                break;
            }
        }
        System.out.println(valid ? "YES" : "NO");
    }
}
