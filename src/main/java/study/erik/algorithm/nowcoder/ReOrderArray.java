package study.erik.algorithm.nowcoder;

import org.junit.Test;

import java.sql.SQLOutput;
import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2019/06/23
 **/
public class ReOrderArray {

    /**
     * description：输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     * @param array
     */
    public void reOrderArray(int[] array) {

        int evenPointer = 0;
        int oddPointer = 0;
        int length = array.length;
        while (true) {
//          1.找到第一个偶数
            while (evenPointer < length && array[evenPointer] % 2 != 0) {
                evenPointer++;
            }
            if(evenPointer == length){
                break; //表示找不到第一个偶数
            }

//          2.找到第一个偶数之后的第一个奇数
            oddPointer = evenPointer + 1;
            while (oddPointer < length && array[oddPointer] % 2 == 0) {
                oddPointer++;
            }
            if (oddPointer == length) {
                break; //表示找不到第一个偶数之后的第一个奇数
            }

//          3.把第一个偶数和第一个奇数之间的数据右循环一个单位--注意这里不是交换，而是循环一定，为了保持奇数与奇数、偶数与偶数之间的相对顺序不变。
            int temp = array[oddPointer];
            oddPointer--;
            while (oddPointer >= evenPointer) {
                array[oddPointer + 1] = array[oddPointer];
                oddPointer--;
            }
            array[evenPointer] = temp;
        }
    }

    @Test
    public void test_reOrderArray() {
        int[] array = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(array));
        reOrderArray(array);
        System.out.println(Arrays.toString(array));

        array = new int[]{1};
        System.out.println(Arrays.toString(array));
        reOrderArray(array);
        System.out.println(Arrays.toString(array));
    }

}
