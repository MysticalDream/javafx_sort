package two.strategy.impl;

/**
 * 基本排序抽象类,主要是对toString的重写使得排序算法实现类放到选择盒子里面有名字显示
 *
 * @author MysticalDream
 */
public abstract class BaseSort {
    String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
