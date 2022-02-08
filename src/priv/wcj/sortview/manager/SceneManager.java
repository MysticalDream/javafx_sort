package two.manager;

import javafx.scene.Scene;
import two.pane.MyPane;
import two.pane.RandomPane;
import two.enums.SceneName;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 场景管理
 *
 * @author MysticalDream
 */
public class SceneManager {

    private static Map<String, Scene> map = new ConcurrentHashMap<>();

    static {
        register(SceneName.MY_SCENE, new Scene(new MyPane(), 600, 400));
        register(SceneName.RANDOM_SCENE, new Scene(new RandomPane(), 600, 500));
    }

    /**
     * 注册场景
     *
     * @param sceneName 场景名
     * @param scene     场景对象
     */
    public static void register(SceneName sceneName, Scene scene) {
        if (sceneName == null || scene == null) {
            return;
        }
        map.putIfAbsent(sceneName.getName(), scene);
    }

    /**
     * 获取场景
     *
     * @param sceneName 场景名
     * @return 返回一个场景对象，如果有的话，反之null
     */
    public static Scene getScene(SceneName sceneName) {
        if (sceneName == null) {
            return null;
        }
        return map.get(sceneName.getName());
    }
}
