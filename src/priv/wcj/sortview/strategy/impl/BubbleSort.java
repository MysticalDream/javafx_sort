package priv.wcj.sortview.strategy.impl;

import priv.wcj.sortview.strategy.Call;
import priv.wcj.sortview.strategy.SortStrategy;

/**
 * 冒泡排序
 *
 * @author MysticalDream
 */
public class BubbleSort extends BaseSort implements SortStrategy {
    public BubbleSort() {
        this.name = "冒泡排序";
    }

    @Override
    public void sort(int[] val, boolean option, Call call) {
        if (option) {
            //升序
            for (int i = 0, len = val.length - 1; i < len; i++) {
                for (int j = 0; j < len - i; j++) {
                    //添加标记
                    call.add(val, j, j + 1);
                    if (val[j] > val[j + 1]) {
                        val[j] = new int[]{val[j + 1], val[j + 1] = val[j]}[0];
                        //添加标记
                        call.add(val, j, j + 1);
                    }

                }

            }
            return;
        }
        //降序
        for (int i = 0, len = val.length - 1; i < len; i++) {
            for (int j = 0; j < len - i; j++) {
                //添加标记
                call.add(val, j, j + 1);
                if (val[j] < val[j + 1]) {
                    val[j] = new int[]{val[j + 1], val[j + 1] = val[j]}[0];
                    //添加标记
                    call.add(val, j, j + 1);
                }

            }

        }
    }

    @Override
    public String getThought() {
        return "冒泡排序：比较相邻的两个元素的大小,以本算法为例：比如3，2，1中按升序来排序，" +
                "先将3和2比较，3大于2，互换3和2，然后将3和1比较，3大于1，互换3和1，最后变为2，1，3，" +
                "重复刚才的过程不过遍历长度都会减少一个元素，因为后边的是已排序完成的了，" +
                "升序是大的靠右,降序是小的靠右,根据升降决定在比较过程中是否需要互换元素位置，冒泡排序是一种稳定的算法";
    }
}
