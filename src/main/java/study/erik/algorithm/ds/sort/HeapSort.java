package study.erik.algorithm.ds.sort;

/**
 * @author erik.wang
 * @date 2020-07-26 08:15
 */
public class HeapSort {

    public static void adjust(int[] heap, int s, int e) {

        //暂存
        int t = heap[s];
        for (int i = 2 * s; i < e; i *= 2) {
            // s的位置已经空出来了，必须从s的两个孩子中出来一个填补。
            if (heap[i] < heap[i + 1]) {
                i++;
            }
            if (heap[i] < t) {
                break;
            }
            //用i去替补了s
            heap[s] = heap[i];
            //然后i就空出来了，用s标注
            s = i;
        }
        heap[s] = t;
    }

    public static void heapSort(int[] heap) {
        for (int i = heap.length / 2; i > 0; i--) {
            adjust(heap, i, heap.length);
        }

        for (int i = heap.length; i > 1; i--) {
            int t = heap[1];
            heap[1] = heap[i];
            heap[i] = t;
            adjust(heap, 1, i - 1);
        }
    }

}
