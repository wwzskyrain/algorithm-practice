package study.erik.algorithm.leetcode.util;

/**
 * @author erik.wang
 * @date 2019/04/21
 **/
public class Util {

    public static int[] getIntArray(String data) {

        String[] intStrArray = data.substring(1, data.length() - 1).split(",");
        int[] result = new int[intStrArray.length];

        for (int i = 0; i < intStrArray.length; i++) {
            result[i] = Integer.valueOf(intStrArray[i]);
        }

        return result;
    }
}
