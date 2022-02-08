package priv.wcj.sortview.strategy.impl;

import priv.wcj.sortview.strategy.Call;
import priv.wcj.sortview.strategy.SortStrategy;

/**
 * 快速排序
 *
 * @author MysticalDream
 */
public class QuickSort extends BaseSort implements SortStrategy {
    public QuickSort() {
        this.name = "快速排序";
    }

    @Override
    public void sort(int[] val, boolean option, Call call) {
        if (option) {
            //升序
            quickSort_up(val, 0, val.length - 1, call);
            return;
        }
        //降序
        quickSort_down(val, 0, val.length - 1, call);
    }

    @Override
    public String getThought() {
        return "快速排序,它的基本思想是：选择一个基值，通过一次排序将要排序的数据分割成独立的两部分，" +
                "一部分比基值都要小，一部分比基值都要大，最后基值位于这两部分之间，" +
                "之后递归调用快速排序对这两部分进行排序，最后可以达到全部有序。快速排序是一种效率很高的不稳定算法，在随机序列中的表现" +
                "更是要优于相同时间复杂度且稳定在O(nlogn)的归并排序，闲人曾经用C语言在debug配置下测试过排序100万的整型“随机数”。" +
                "快排算法只用了0.2s release配置下只用0.06秒。不过我一开始选取的基值是第一个元素，这是错误的做法，在有序序列表现就很差了,因为所有元素都会划分到一个区域，排序时间将会是二次的，" +
                "一般随机挑选基值比较好,比如三数中值分割法选取基值";
    }

    /**
     * 获取中值
     *
     * @param arr  整型数组
     * @param low  开始位置下标
     * @param high 结束位置下标
     * @return 返回中间的位置的值
     */
    private int median(int[] arr, int low, int high) {
        int mid = (low & high) + ((low ^ high) >>> 1);
        arr[mid] = new int[]{arr[high], arr[high] = arr[mid]}[0];
        return arr[high];
    }

    /**
     * 快速排序--升序
     *
     * @param arr   整型数组
     * @param left  左边界
     * @param right 右边界
     */
    private void quickSort_up(int[] arr, int left, int right, Call call) {

        if (left < right) {
            int pivot = median(arr, left, right);
            call.add(arr, left, left);
            int low = left;
            int high = right;
            while (low < high) {
                while (low < high && arr[low] < pivot) {
                    call.add(arr, low, left);
                    low++;
                }
                if (low < high) {
                    arr[high--] = arr[low];
                    call.add(arr, high + 1, low);
                }

                while (low < high && arr[high] > pivot) {
                    call.add(arr, high, left);
                    high--;
                }
                if (low < high) {
                    arr[low++] = arr[high];
                    call.add(arr, low - 1, high);
                }
            }
            arr[low] = pivot;
            call.add(arr, low, low);
            quickSort_up(arr, left, low - 1, call);
            quickSort_up(arr, low + 1, right, call);
        }
    }

    /**
     * 快速排序--降序
     *
     * @param arr   整型数组
     * @param left  左边界
     * @param right 右边界
     */
    private void quickSort_down(int[] arr, int left, int right, Call call) {
        if (left < right) {
            int pivot = median(arr, left, right);
            call.add(arr, left, left);
            int low = left;
            int high = right;
            while (low < high) {
                while (low < high && arr[low] > pivot) {
                    call.add(arr, low, left);
                    low++;
                }
                if (low < high) {
                    arr[high--] = arr[low];
                    call.add(arr, high + 1, low);
                }
                while (low < high && arr[high] < pivot) {
                    call.add(arr, high, left);
                    high--;
                }
                if (low < high) {
                    arr[low++] = arr[high];
                    call.add(arr, low - 1, high);
                }
            }
            arr[low] = pivot;
            call.add(arr, low, low);
            quickSort_down(arr, left, low - 1, call);
            quickSort_down(arr, low + 1, right, call);
        }
    }
}
