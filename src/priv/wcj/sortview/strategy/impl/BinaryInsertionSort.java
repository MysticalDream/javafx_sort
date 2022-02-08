package two.strategy.impl;

import two.strategy.Call;
import two.strategy.SortStrategy;

/**
 * 二分法插入排序
 * @author MysticalDream
 */
public class BinaryInsertionSort extends BaseSort implements SortStrategy {
    public BinaryInsertionSort() {
        this.name = "二分法插入排序";
    }

    @Override
    public void sort(int[] val, boolean option, Call call) {
        if (option) {
            //升序
            for (int i = 1, len = val.length; i < len; i++) {
                //左边界
                int begin = 0;
                //右边界
                int end = i - 1;
                int key = val[i];
                while (begin <= end) {
                    int middle = (begin & end) + ((begin ^ end) >>> 1);
                    //保证稳定性
                    if (val[middle] > key) {
                        end = middle - 1;
                    } else {
                        begin = middle + 1;
                    }
                    call.add(val, middle, i);
                }
                for (int j = i - 1; j >= begin; j--) {
                    val[j + 1] = val[j];
                }
                val[begin] = key;
                call.add(val, begin, begin);
            }
            return;
        }
        //降序
        for (int i = 1, len = val.length; i < len; i++) {
            int begin = 0;
            //右边界
            int end = i - 1;
            int key = val[i];
            while (begin <= end) {
                int middle = (begin & end) + ((begin ^ end) >>> 1);
                //保证稳定性
                if (val[middle] > key) {
                    begin = middle + 1;
                } else {
                    end = middle - 1;
                }
                call.add(val, middle, i);
            }
            for (int j = i - 1; j >= begin; j--) {
                val[j + 1] = val[j];
            }
            val[begin] = key;
            call.add(val, begin, begin);
        }
    }

    @Override
    public String getThought() {
        return "二分法插入排序，在原来算法思想的前提下在寻找位置的时候使用二分查找的思想,即每次查找都在有序数列进行折半查找，" +
                "如果是升序，想和待插入的有序列表的中间位置的元素进行对比，如果待插入的元素比该位置的值大，" +
                "说明该位置应该在中间位置之后，接着将搜索范围缩小到该中间位置的后一位到数据序列的尾部，" +
                "如果待插入的元素比该位置的值小，说明该位置应该在中间位置之前，接着将搜索范围缩小到该中间位置的前一位到数据序列的头部" +
                "直到最后搜索范围缩小到一位，最后即可找到合适位置。二分法排序算法也是稳定算法，不过在算法中需要注意插入位置，否则就不是稳定算法";
    }
}
