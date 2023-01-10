/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string.easy;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : DefangingAnIPAddress.java, v 0.1 2023年01月09日 22:46 yueyi Exp $
 */
public class DefangingAnIPAddress {

    @LetCodeCommit(title = "1108. Defanging an IP Address")
    public String defangIPaddr(String address) {
        return address.replaceAll("\\.", "[.]");
    }
}