package two.strategy;

import two.strategy.impl.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 策略工厂
 * @author MysticalDream
 */
public class SortStrategyFactory {
    private static List<SortStrategy> list = new ArrayList<>();
    //添加策略
    static {
        list.add(new BubbleSort());
        list.add(new SelectSort());
        list.add(new InsertionSort());
        list.add(new BinaryInsertionSort());
        list.add(new QuickSort());
        list.add(new MergeSort());
    }

    public static List<SortStrategy> getLists() {
        return list;
    }
}
