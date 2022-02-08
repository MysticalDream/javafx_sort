package priv.wcj.sortview;

import javafx.application.Application;
import javafx.stage.Stage;
import priv.wcj.sortview.manager.SceneManager;
import priv.wcj.sortview.manager.StageManager;
import priv.wcj.sortview.enums.SceneName;
import priv.wcj.sortview.enums.StageName;

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
