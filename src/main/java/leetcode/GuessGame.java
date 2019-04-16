package leetcode;

/*
374. 猜数游戏

总结：这个题的解发就是一个典型的折半查找。

attention1：变换low和high指针时，low要为mid+1；这是因为除2操作是向下取整了。
attention2：注意题目中给的guess函数的参数和返回值都是int，如果low和high都也用int，
    就会再两者相加时溢出，从而再除2求的mid就不对了；也正因为这样，提交上的代码再case为>Integer.MAX_VALUE
    时超时。修改：只需要用long型来存储low和high就好了。


*/

public class GuessGame {

    int target;


    public int guessNumber(int n) {

        long low = 1;
        long high = n;

        while (true) {

            int mid = (int)((low + high) / 2);

            int g = guess(mid);

            if (g == 0) {
                return mid;
            } else if (g == -1) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

    }


    public static void main(String[] args) {


        System.out.println(Integer.MAX_VALUE);

    }

    int guess(int num) {
        if (num < target) {
            return 1;
        } else if (num == target) {
            return 0;
        } else
            return -1;
    }

}

