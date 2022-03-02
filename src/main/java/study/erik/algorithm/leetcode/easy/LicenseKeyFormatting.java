/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : LicenseKeyFormatting.java, v 0.1 2022年03月02日 7:45 上午 yueyi Exp $
 */
public class LicenseKeyFormatting {

    @LetCodeCommit(title = "482. License Key Formatting")
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int sbLength = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '-') {
                continue;
            }
            if (c <= 'z' && c >= 'a') {
                c -= ('a' - 'A');
            }
            sb.append(c);
            sbLength++;
            if (sbLength % k == 0) {
                sb.append('-');
            }
        }
        sb = sb.reverse();
        return (sb.length() > 1 && sb.charAt(0) == '-') ? sb.substring(1) : sb.toString();
    }

}