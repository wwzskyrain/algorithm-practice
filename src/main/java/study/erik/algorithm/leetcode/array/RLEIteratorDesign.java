/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : RLEIterator.java, v 0.1 2022年06月03日 09:11 yueyi Exp $
 */
@LetCodeCommit(title = "900. RLE Iterator",
        diff = "m",
        selfRemark = "设计题，一般对性能要求不好，关键在逻辑设计.")
public class RLEIteratorDesign {

    public static class RLEIterator {

        public int[] encoding;
        int idx = 0;

        public RLEIterator(int[] encoding) {
            this.encoding = encoding;
        }

        public int next(int n) {
            if (idx > encoding.length - 1) {
                return -1;
            }
            if (idx == encoding.length - 2 && encoding[idx] == 0) {
                return -1;
            }
            while (encoding[idx] < n) {
                n -= encoding[idx];
                idx += 2;
                if (idx >= encoding.length) {
                    return -1;
                }
            }
            encoding[idx] -= n;
            return encoding[idx + 1];
        }
    }

    public static void main(String[] args) {
        RLEIterator iterator = new RLEIterator(ArrayUtils.buildArray("[3, 8, 0, 9, 2, 5]"));
        System.out.println(iterator.next(2));
        System.out.println(iterator.next(1));
        System.out.println(iterator.next(1));
        System.out.println(iterator.next(2));

    }

}