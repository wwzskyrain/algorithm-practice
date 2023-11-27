package study.erik.algorithm.job.zijie.simulation13;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/21 11:31
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_3 {

    @LetCodeCommit(title = "这个题目我们会，硬着头皮手写下来了。拜托了idea的调试的心酸。")
    public int maximalRectangle(String[] matrix) {
        int n = matrix.length;
        if(n == 0) {
            return 0;
        }
        int m = matrix[0].length();

        int[] h = new int[m];
        int maxArea = 0;
        for(int i = 0; i < n; i++) {
            String s = matrix[i];
            for(int j = 0; j < m; j++) {
                if(s.charAt(j) == '0') {
                    h[j] = 0;
                }else {
                    h[j]++;
                }
            }
            maxArea = Math.max(maxArea, calMaxArea(h));
            // to ji suan
        }
        return maxArea;
    }

    public int calMaxArea(int[] heights){
        int n = heights.length;
        int[] stack = new int[n + 1];
        int idx = 0;
        int[] r = new int[n];
        for(int i = 0; i < n; i++) {
            int h = heights[i];
            while(idx!=0 && heights[stack[idx-1]] > h) {
                r[stack[idx-1]] = i;
                idx--;
            }
            stack[idx++] = i;
        }
        //用0清理栈
        while(idx!=0 && heights[stack[idx-1]] > 0) {
            r[stack[idx-1]] = n;
            idx--;
        }
        stack[idx++] = n;

        int[] l = new int[n];
        idx = 0;
        for(int i = n-1; i >= 0; i--) {
            int h = heights[i];
            while(idx!=0 && heights[stack[idx-1]] > h) {
                l[stack[idx-1]] = i;
                idx--;
            }
            stack[idx++] = i;
        }
        //用0清理栈
        while(idx!=0 && heights[stack[idx-1]] > 0) {
            l[stack[idx-1]] = -1;
            idx--;
        }
        stack[idx++] = -1;

        int m = 0;
        for(int i=0; i < n; i++) {
            int area = heights[i] * (r[i] - l[i] - 1);
            m = Math.max(m, area);
        }
        return m;
    }

}
