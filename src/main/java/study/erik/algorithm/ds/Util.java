package study.erik.algorithm.ds;

/**
 * @author erik.wang
 * @date 2019/06/30
 **/
public class Util {

    public static void printMatrix(int[][] matrix) {

        StringBuilder stringBuilder;
        for (int i = 0; i < matrix.length; i++) {
            stringBuilder = new StringBuilder();
            for (int j = 0; j < matrix[i].length; j++) {
                stringBuilder.append(matrix[i][j]).append("  ");
            }
            System.out.println(stringBuilder.toString());
        }
    }

}
