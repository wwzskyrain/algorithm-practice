package study.erik.algorithm.ds.string;

/**
 * @author erik.wang
 * @date 2020-05-09 08:06
 */
public class KMPWithDFA {


    /**
     * 在有限状态机自动机的解法中，没有了字符比较，只有'字符输入、更新状态'；
     * 即使在构造自动机的时候，也是'字符输入、更新状态';
     *
     * @param target
     * @param pattern
     * @return
     */
    public static int search(String target, String pattern) {

        int[][] dfa = new int[26][pattern.length() + 1];

        //构造状态机时刻
        int i, cStatus, j;
        int c;
        dfa[pattern.charAt(0)][0] = 1;
        for (j = 0, cStatus = 0; j < pattern.length(); j++) {

            //cStatus 表示当前的状态（current status）
            for (int i1 = 0; i1 < dfa.length; i1++) {
                dfa[i1][j] = dfa[i1][cStatus];
            }
            c = pattern.charAt(j);
            //这一步是可想而知的
            dfa[c - 'a'][j] = j + 1;
            //更新当前状态——用当前真正的pattern[j]去'刺激'它
            cStatus = dfa[c - 'a'][cStatus];
        }


        // 匹配时刻
        for (i = 0, cStatus = 0; i < target.length() && cStatus < target.length(); i++) {
            cStatus = dfa[target.charAt(i) - 'a'][cStatus];
        }

        if (cStatus == target.length()) {
            return i - target.length();
        } else {
            return -1;
        }
    }

}
