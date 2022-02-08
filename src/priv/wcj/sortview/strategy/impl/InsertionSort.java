package priv.wcj.sortview.strategy.impl;

import priv.wcj.sortview.strategy.Call;
import priv.wcj.sortview.strategy.SortStrategy;

/**
 * 插入排序
 * @author MysticalDream
 */
public class InsertionSort extends BaseSort implements SortStrategy {
    public InsertionSort() {
        this.name = "插入排序";
    }

    @Override
    public void sort(int[] val, boolean option, Call call) {
        if (option) {
            //升序
            for (int i = 1, len = val.length; i < len; i++) {
                int current = val[i];
                int prev = i - 1;
                while (prev > -1 && val[prev] > current) {
                    call.add(val, prev, i);
                    val[prev + 1] = val[prev];
                    prev--;
                }
                call.add(val, prev, i);
                val[prev + 1] = current;
                call.add(val, prev + 1, prev + 1);
            }
            return;
        }
        //降序
        for (int i = 1, len = val.length; i < len; i++) {
            int current = val[i];
            int prev = i - 1;
            while (prev > -1 && val[prev] < current) {
                call.add(val, prev, i);
                val[prev + 1] = val[prev];
                prev--;

            }
            call.add(val, prev, i);
            val[prev + 1] = current;
            call.add(val, prev + 1, prev + 1);
        }
    }

    @Override
    public String getThought() {
        return "插入排序核心就是将一个数据插入到一个有序数据中，起初数组第一个默认有序，接着将第二个与第一个数据比较，" +
                "根据升降序决定是否要互换，以升序为例：3，2，1序列，3一开始默认有序，到下一位2，" +
                "将2与前边有序序列比较，找到合适位置，这里先和3比较，2小于3，所以2应该在3前面，3后移一位，" +
                "原来3的位置空出，由于3是第一个元素，前面已经没有数据，" +
                "所以将保存的2插到第一位，现在2，3已经是有序的了，接下来到1" +
                "1同样执行相同步骤，先和3比较，3大于1，3后移一位，接着和2比较，2后移一位最后确定1的位置，将1插入该位置，" +
                "插入排序是一种稳定的排序算法";
    }
}
