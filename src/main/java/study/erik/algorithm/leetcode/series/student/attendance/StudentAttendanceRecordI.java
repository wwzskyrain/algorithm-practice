/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.student.attendance;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : StudentAttendanceRecordI.java, v 0.1 2021年08月17日 9:05 上午 yueyi Exp $
 */
public class StudentAttendanceRecordI {

    @LetCodeCommit(title = "Student Attendance Record I",
            selfRemark = "大一学生的题目，太简单")
    public boolean checkRecord(String s) {

        int cA = 0;
        int cL = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'L') {
                cL++;
                if (cL >= 3) {
                    return false;
                }
            } else {
                cL = 0;
                if (c == 'A') {
                    cA++;
                    if (cA >= 2) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}