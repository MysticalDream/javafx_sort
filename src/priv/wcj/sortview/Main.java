package two;

import javafx.application.Application;
import javafx.stage.Stage;
import two.manager.SceneManager;
import two.manager.StageManager;
import two.enums.SceneName;
import two.enums.StageName;

/**
 * 主入口主类
 * @author MysticalDream
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //注册舞台
        StageManager.register(StageName.PRIMARY_STAGE, primaryStage);
        //设置舞台场景
        StageManager.setScene(StageName.PRIMARY_STAGE, SceneManager.getScene(SceneName.MY_SCENE));
        //展示舞台
        StageManager.showStage(StageName.PRIMARY_STAGE);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
