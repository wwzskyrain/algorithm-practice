package study.erik.algorithm.leetcode.sort1;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.leetcode.util.Util;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2019/04/21
 **/
public class Solution {


    /**
     * title =  Merge Sorted Array
     * 注意：参数nums1和nums2已经排好序了
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }

        int i = m - 1, j = n - 1;

        for (; i >= 0 && j >= 0; ) {
            if (nums1[i] > nums2[j]) {
                nums1[i + j + 1] = nums1[i];
                i--;
            } else {
                nums1[i + j + 1] = nums2[j];
                j--;
            }
        }

        while (j >= 0) {
            nums1[j] = nums2[j];
            j--;
        }

    }


    /**
     * 快速排序
     *
     * @param data
     * @param low
     * @param high
     */
    public void quickSort(int[] data, int low, int high) {

        if (low < high) {
            int mid = quickSortPartition(data, low, high);
            quickSort(data, low, mid - 1);
            quickSort(data, mid + 1, high);
        }

    }

    private int quickSortPartition(int[] data, int low, int high) {

        int temp = data[low];
        while (low < high) {
            while (low < high && data[high] >= temp) {
                high--;
            }
            data[low] = data[high];
            while (low < high && data[low] <= temp) {
                low++;
            }
            data[high] = data[low];
        }

        data[low] = temp;
        return low;
    }

    @Test
    public void test_sort() {
        int[] data = Util.getIntArray("[49,38,65,97,76,13,27,49]");
        int[] expect = new int[]{13, 27, 38, 49, 49, 65, 76, 97};
        System.out.println(Arrays.toString(expect));

        int[] copy = Arrays.copyOf(data, data.length);
        quickSort(copy, 0, copy.length - 1);
        System.out.println(Arrays.toString(copy));

        int[] copy2 = new int[data.length + 1];
        for (int i = 0; i < data.length; i++) {
            copy2[i + 1] = data[i];
        }
        copy2[0] = 0;

        heapSort(copy2);
        System.out.println(Arrays.toString(Arrays.copyOfRange(copy2, 1, copy2.length)));
    }

    public void heapSort(int[] data) {

        //从data的倒数第二层开始，一直到第一层(第一层就是data[0].)，调整为一颗大顶堆
        for (int i = data.length / 2; i >= 1; i--) {
            heapAdjust(data, i, data.length - 1);
        }

        //每次摘取大顶堆的'顶'，注意顶是data[1]，不是data[0]，然后再进行一次调整，调整从data[0]开始到[i]结束。
        for (int i = data.length - 1; i >= 1; i--) {
            int temp = data[i];
            data[i] = data[1];
            data[1] = temp;
            heapAdjust(data, 1, i - 1);
        }
    }


    /**
     * 调整成一个大顶堆，调整对象是以start为根的子树，
     * 子树边际是不大于end(有可能end都不再start的子树结点中)。
     *
     * @param data
     * @param start
     * @param end
     */
    private void heapAdjust(int[] data, int start, int end) {

        //整个函数都是一个'交换'过程
        int temp = data[start];

        for (int i = 2 * start; i <= end; i *= 2) {

            if (i < end && data[i] < data[i + 1]) { //在左孩子、右孩子中，找打大的那一个。
                i++;
            }
            if (temp > data[i]) //如果已经构成堆了，循环结束，即找到了temp的落脚点
                break;

            data[start] = data[i];
            start = i;

        }

        data[start] = temp;
    }





}
