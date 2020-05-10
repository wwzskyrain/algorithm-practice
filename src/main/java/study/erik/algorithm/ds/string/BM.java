package study.erik.algorithm.ds.string;

/**
 * @author erik.wang
 * @date 2020-05-10 22:02
 * 宝页猫算法
 */
public class BM {

    /**
     * 坏字符算法
     *
     * @return
     */
    public static int badChar(String target, String pattern) {

        int[] lastIndex = new int[26];
        // TODO: 2020-05-10 构造index 数组
        for (int i = 0; i < 26; i++) {
            lastIndex[i] = -1;
        }

        for (int i = 0; i < pattern.length(); i++) {
            lastIndex[pattern.charAt(i) - 'a'] = i;
        }

        int tLen = target.length();
        int pLen = pattern.length();

        for (int i = 0; i <= tLen - pLen; ) {
            int skip = 0;
            for (int j = tLen - 1; j >= 0; j--) {
                char cChar;
                if (pattern.charAt(j) != (cChar = target.charAt(i + j))) {
                    skip = j - lastIndex[cChar - 'a'];
                    skip = Math.max(1, skip);
                    break;
                }
            }
            if (skip == 0) {
                return i;
            }
            i += skip;
        }
        return -1;
    }

    /**
     * 好后缀算法
     *
     * @param target
     * @param pattern
     * @return
     */
    public static int goodPostfix(String target, String pattern) {
        // TODO: 2020-05-10
        return -1;
    }


}
