package two.strategy;

/**
 * 回调接口
 * @author MysticalDream
 */
public interface Call {
    /**
     * 添加结果数组
     * @param val 整型数组
     * @param index1 要标记的下标
     * @param index2 要标记的下标
     */
    void add(int[] val, int index1, int index2);

}
