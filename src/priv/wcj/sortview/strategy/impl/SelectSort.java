package two.strategy.impl;

import two.strategy.Call;
import two.strategy.SortStrategy;

/**
 * 选择排序
 * @author MysticalDream
 */
public class SelectSort extends BaseSort implements SortStrategy {
    public SelectSort() {
        this.name = "选择排序";
    }

    @Override
    public void sort(int[] val, boolean option, Call call) {
        if (option) {
            //升序
            for (int i = 0, len = val.length; i < len - 1; i++) {
                int index = i;
                for (int j = i + 1; j < len; j++) {
                    call.add(val, index, j);
                    if (val[index] > val[j]) {
                        index = j;
                    }
                }
                val[index] = new int[]{val[i], val[i] = val[index]}[0];
                call.add(val, index, i);
            }
            return;
        }
        //降序
        for (int i = 0, len = val.length; i < len - 1; i++) {
            int index = i;
            for (int j = i + 1; j < len; j++) {
                call.add(val, index, j);
                if (val[index] < val[j]) {
                    index = j;
                }
            }
            val[index] = new int[]{val[i], val[i] = val[index]}[0];
            call.add(val, index, i);
        }
    }

    @Override
    public String getThought() {

        return "选择排序每一次从待排序的数据元素中选出最小（或最大）的一个元素，将其和数据序列的起始位置的元素互换，" +
                "每次重复该操作，将数据从小（大）到大（小）依次排序出来" +
                "直到全部待排序的数据元素排完。选择排序是一种不稳定的排序算法";
    }
}
