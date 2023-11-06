package study.erik.algorithm.job.huawei.simulation05;

public class Main_3 {

    public int minAddToMakeValid(String s) {
        int l = s.length();
        char[] stack = new char[l];
        int idx = 0;
        for (int i = 0; i < l; i++) {
            char c = s.charAt(i);
            if (idx == 0) {
                stack[idx++] = c;
            } else {
                if (c == ')' && stack[idx - 1] == '(') {
                    idx--;
                } else {
                    stack[idx++] = c;
                }
            }
        }
        return idx;
    }

}
