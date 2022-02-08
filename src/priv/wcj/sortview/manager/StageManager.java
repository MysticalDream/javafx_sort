package priv.wcj.sortview.manager;

import javafx.scene.Scene;
import javafx.stage.Stage;
import priv.wcj.sortview.enums.StageName;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 舞台管理
 *
 * @author MysticalDream
 */
public class StageManager {

    private static Map<String, Stage> map = new ConcurrentHashMap<>();

    static {
        register(StageName.MY_STAGE, new Stage());
    }

    /**
     * 注册，添加舞台
     *
     * @param stageName 舞台名字
     * @param stage     舞台实例
     */
    public static void register(StageName stageName, Stage stage) {
        if (stage == null || stage == null) {
            return;
        }
        map.putIfAbsent(stageName.getName(), stage);
    }

    /**
     * 获取舞台对象实例
     *
     * @param name 舞台名字
     * @return 返回舞台实例对象
     */
    public static Stage getStageByName(StageName name) {
        if (name == null) {
            return null;
        }
        return map.get(name.getName());
    }

    /**
     * 设置场景
     *
     * @param stageName 舞台名字
     * @param scene     场景
     */
    public static void setScene(StageName stageName, Scene scene) {
        if (stageName == null || scene == null) {
            return;
        }
        map.get(stageName.getName()).setScene(scene);
    }

    /**
     * 展示舞台
     *
     * @param name 舞台的名字
     */
    public static void showStage(StageName name) {
        Stage stageByName = getStageByName(name);
        if (stageByName == null) {
            return;
        }
        stageByName.show();
    }

    /**
     * 关闭舞台
     *
     * @param name 舞台名字
     */
    public static void closeStage(StageName name) {
        if (name == null) {
            return;
        }
        getStageByName(name).close();
    }
}
