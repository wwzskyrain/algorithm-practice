package study.erik.algorithm.classic;

/**
 * @Date 2019-08-14
 * @Created by erik
 * 二分法和牛顿法开根号
 * 参考文章 https://www.cnblogs.com/wangkundentisy/p/8118007.html
 */
public class SquareRootCalculations {

    public static double solutionByBisectionMethod(double n) {

        double precision = Math.exp(-6);

        double low = 0;
        double high = n;
        double mid = (low + high) / 2;

        while (Math.abs(mid * mid - n) > precision) {

            double diff = mid * mid - n;
            if (diff > 0) {
                high = mid;
            } else if (diff < 0) {
                low = mid;
            } else {
                return mid;
            }
            mid = (low + high) / 2;
        }

        return mid;
    }

    /**
     * 牛顿法主要有一个退到公式；知道了退到之后的公式后，转化成代码就可以了。
     * @param n
     * @return
     */
    public static double solutionByNewtonsMethod(double n) {

        double result = 1;
        double precision = Math.exp(-6);

        while (Math.abs(result * result - n) > precision) {
            result = 0.5 * (result + n / result);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solutionByNewtonsMethod(82));
        System.out.println(solutionByBisectionMethod(82));
    }

}
