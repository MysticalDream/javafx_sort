package priv.wcj.sortview.enums;

/**
 * 舞台名字枚举
 *
 * @author MysticalDream
 */

public enum StageName {
    /**
     * 主舞台
     */
    PRIMARY_STAGE("primaryStage"),
    MY_STAGE("my_stage");

    private String name;

    StageName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
