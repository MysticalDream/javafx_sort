package priv.wcj.sortview.enums;

/**
 * 场景名字枚举
 * @author MysticalDream
 */

public enum SceneName {
    /**
     * 一个样式
     */
    MY_SCENE("my_scene"),
    RANDOM_SCENE("random_scene");
    private String name;

    SceneName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
