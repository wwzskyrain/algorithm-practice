package study.huawei.medium;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

//HJ98 喜欢切数组的红
//https://www.nowcoder.com/practice/74cb703f25dc4956acb3b08028a1f4b4?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
@Slf4j
public class HongCutArray {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String[] numStrArr = in.nextLine().split(" ");
        int[] data = new int[n];
        int[] preSum = new int[n];
        for (int i = 0; i < data.length; i++) {
            data[i] = Integer.parseInt(numStrArr[i]);
            if (i == 0) {
                preSum[i] = data[i];
            } else {
                preSum[i] = preSum[i - 1] + data[i];
            }
        }
        int total = preSum[preSum.length - 1];
        if (total % 3 != 0) {
            System.out.println(0);
            return;
        }
        int count = 0;
        int three = total / 3;
        boolean find1 = false;
        for (int i = 0; i < data.length - 2; i++) {
            int j = i;
            if (!find1) {
                while (j < data.length && data[j] <= 0) {
                    j++;
                }
                if (j < data.length) {
                    find1 = true;
                } else {
                    break;
                }
            }
            while (j < data.length && preSum[j] != three) {
                j++;
            }
            if (j >= data.length) {
                break;
            }
            i = j;
            boolean find2 = false;
            for (int k = i + 1; k < data.length; k++) {
                //第二次分割
                j = k;
                if (!find2) {
                    while (j < data.length && data[j] <= 0) {
                        j++;
                    }
                    if (j < data.length) {
                        find2 = true;
                    } else {
                        break;
                    }
                }
                while (j < data.length && preSum[j] != 2 * three) {
                    j++;
                }
                if (j >= data.length) {
                    // 没找到第二段的1/3
                    break;
                }
                k = j;
                for (int l = k + 1; l < data.length; l++) {
                    // 第三段了，不用检查第三段的和是否等于1/3了，因为一定的
                    if (data[l] > 0) {
                        count++;
                        break;
                    }
                }
                //
            }
        }
        System.out.println(count);
    }

}
