package two.strategy;

/**
 * 排序算法策略接口
 *
 * @author MysticalDream
 */
public interface SortStrategy {
    /**
     * 对数组排序
     *
     * @param val    待排序的数组
     * @param option 排序的方式，true为以升序排序，false为降序
     * @param call   回调接口实现
     */
    void sort(int[] val, boolean option, Call call);

    /**
     * 获取算法思想
     *
     * @return 返回算法思想介绍字符串
     */
    String getThought();
}
