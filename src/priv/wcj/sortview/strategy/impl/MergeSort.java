package priv.wcj.sortview.strategy.impl;

import priv.wcj.sortview.strategy.Call;
import priv.wcj.sortview.strategy.SortStrategy;


/**
 * @author MysticalDream
 */
public class MergeSort extends BaseSort implements SortStrategy {
    private int[] bucketValue;

    public MergeSort() {
        this.name = "归并排序";
    }

    @Override
    public void sort(int[] val, boolean option, Call call) {
        bucketValue = new int[val.length];
        if (option) {
            //升序
            divide_up(val, 0, val.length - 1, call);
            return;
        }
        //降序
        divide_down(val, 0, val.length - 1, call);
    }

    @Override
    public String getThought() {
        return "归并排序是分治思想的典型算法,先将待排序的序列分成一个个小部分，再将其合并成为有序序列。" +
                "归并排序就是把序列先一分为二，直到分解成单独一个，每一个单独个体都是有序的，" +
                "最后将两个有序序列进行合并成另一个有序序列，再将该序列不断合并回溯，最后合并成一个有序序列，该方法即二路归并。" +
                "该归并排序算法是时间复杂度稳定在O(nlogn)的稳定算法";
    }

    /**
     * 升序分割
     *
     * @param arr   整型数组
     * @param left  左边界
     * @param right 右边界
     */
    private void divide_up(int[] arr, int left, int right, Call call) {
        if (left < right) {
            int mid = (left & right) + ((left ^ right) >>> 1);
            divide_up(arr, left, mid, call);
            divide_up(arr, mid + 1, right, call);
            merge_up(arr, left, mid, right, call);
        }
    }

    /**
     * 降序分割
     *
     * @param arr   整型数组
     * @param left  左边界
     * @param right 右边界
     */
    private void divide_down(int[] arr, int left, int right, Call call) {
        if (left < right) {
            int mid = (left & right) + ((left ^ right) >>> 1);
            divide_down(arr, left, mid, call);
            divide_down(arr, mid + 1, right, call);
            merge_down(arr, left, mid, right, call);
        }
    }

    /**
     * 升序归并
     *
     * @param arr   整型数组
     * @param left  左边界
     * @param mid   中间位置
     * @param right 右边界
     */
    private void merge_up(int[] arr, int left, int mid, int right, Call call) {
        int i = left, j = mid + 1, k = 0;
        call.add(arr, left, right);
        for (; i <= mid && j <= right; k++) {
            //保持稳定性
            if (arr[i] > arr[j]) {
                bucketValue[k] = arr[j];
                j++;
            } else {
                bucketValue[k] = arr[i];
                i++;
            }
        }
        while (i <= mid) {
            bucketValue[k++] = arr[i++];
        }
        while (j <= right) {
            bucketValue[k++] = arr[j++];
        }
        int tempLeft = left;
        for (int l = 0, s = right - left + 1; l < s; l++) {
            arr[left++] = bucketValue[l];
        }
        call.add(arr, tempLeft, right);
    }

    /**
     * 降序归并
     *
     * @param arr   整型数组
     * @param left  左边界
     * @param mid   中间位置
     * @param right 右边界
     */
    private void merge_down(int[] arr, int left, int mid, int right, Call call) {
        int i = left, j = mid + 1, k = 0;
        call.add(arr, left, right);
        for (; i <= mid && j <= right; k++) {
            //保持稳定性
            if (arr[i] < arr[j]) {
                bucketValue[k] = arr[j];
                j++;
            } else {
                bucketValue[k] = arr[i];
                i++;

            }
        }
        while (i <= mid) {
            bucketValue[k++] = arr[i++];
        }
        while (j <= right) {
            bucketValue[k++] = arr[j++];
        }
        int tempLeft = left;
        for (int l = 0, s = right - left + 1; l < s; l++) {
            arr[left++] = bucketValue[l];
        }
        call.add(arr, tempLeft, right);
    }
}
