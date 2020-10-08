package study.erik.algorithm.ds.search;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 */
public class BinarySearch {

    /**
     * 最基础的二分查找
     *
     * @param elements
     * @param target
     * @return 找到返回下标，找不到返回-1
     */
    public static int basicBinarySearch(int[] elements, int target) {
        int l = 0;
        int h = elements.length - 1;
        while (l <= h) {    //搜索区间是'左闭右闭'
            int m = (h - l) / 2 + l;
            if (elements[m] == target) {
                return target;
            }
            if (elements[m] < target) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找-如果查到，就返回正数；否则返回负数=(-插入位置-1）
     * 设插入位置为 i， 则 ret = （-i-1）= -（i+1）--> i = -ret-1
     * 注意：没有重复的元素。
     * todo 如果有重复的元素的话，是否依然适用
     *
     * @param elements
     * @param target
     * @return
     */
    public static int binarySearchInsertIndex(int[] elements, int target) {

        int l = 0;
        int h = elements.length - 1;
        while (l <= h) {
            int m = (h - l) / 2 + l;
            int midV = elements[m];
            if (midV == target) {
                return m;
            }
            if (midV > target) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -l - 1; //为了区分-0和+0，这里在取负之后在往左平移一位
    }

    @Test
    public void test_binary_search_insert_index() {
        int[] elements = {1, 3, 5, 7, 9, 11, 12, 13, 15, 17};
        Assert.assertEquals(0, binarySearchInsertIndex(elements, 1));
        Assert.assertEquals(-2, binarySearchInsertIndex(elements, 2));
        Assert.assertEquals(1, binarySearchInsertIndex(elements, 3));
        Assert.assertEquals(-3, binarySearchInsertIndex(elements, 4));
        Assert.assertEquals(2, binarySearchInsertIndex(elements, 5));
        Assert.assertEquals(-4, binarySearchInsertIndex(elements, 6));
        Assert.assertEquals(3, binarySearchInsertIndex(elements, 7));
        Assert.assertEquals(-5, binarySearchInsertIndex(elements, 8));
        Assert.assertEquals(4, binarySearchInsertIndex(elements, 9));
        Assert.assertEquals(-6, binarySearchInsertIndex(elements, 10));
        Assert.assertEquals(9, binarySearchInsertIndex(elements, 17));
        Assert.assertEquals(-11, binarySearchInsertIndex(elements, 18));
        Assert.assertEquals(-11, binarySearchInsertIndex(elements, 19));
    }

    /**
     * 返回小于target的个数。
     * 可以改造一下，返回插入位置
     *
     * @param elements
     * @param target
     * @return
     */
    public static int binarySearchLeftBound(int[] elements, int target) {

        if (elements.length == 0) return -1;
        int left = 0;
        int right = elements.length; // 注意

        while (left < right) { //搜索空间是'左闭右开'
            int mid = (left + right) / 2;
            if (elements[mid] == target) {
                right = mid;    //缩小右边界
            } else if (elements[mid] < target) {
                left = mid + 1;
            } else if (elements[mid] > target) {
                right = mid; // 注意
            }
        }
        return left; //left和right是一致的。如果有target，则返回最左边的target；
        // 如果没有target，则返回小于target的元素个数

        /** 改造返回插入位置：
         *  if(element[left] == target){
         *      return left;
         *  }else{
         *      return -left - 1;
         *  }
         *
         *  即: return element[left] == target ? left : -left - 1;
         */
    }


    @Test
    public void test_binary_search_left_bound() {
        int[] elements = {2, 2, 2, 4, 4, 4, 4, 6, 8, 9};
        Assert.assertEquals(binarySearchLeftBound(elements, 0), 0);
        Assert.assertEquals(binarySearchLeftBound(elements, 1), 0);
        Assert.assertEquals(binarySearchLeftBound(elements, 2), 0);
        Assert.assertEquals(binarySearchLeftBound(elements, 3), 3);
        Assert.assertEquals(binarySearchLeftBound(elements, 4), 3);
        Assert.assertEquals(binarySearchLeftBound(elements, 5), 7);
        Assert.assertEquals(binarySearchLeftBound(elements, 6), 7);
        Assert.assertEquals(binarySearchLeftBound(elements, 7), 8);
        Assert.assertEquals(binarySearchLeftBound(elements, 8), 8);
        Assert.assertEquals(binarySearchLeftBound(elements, 9), 9);
        Assert.assertEquals(binarySearchLeftBound(elements, 10), 10);
        Assert.assertEquals(binarySearchLeftBound(elements, 11), 10);
        Assert.assertEquals(binarySearchLeftBound(elements, 12), 10);
    }


}
