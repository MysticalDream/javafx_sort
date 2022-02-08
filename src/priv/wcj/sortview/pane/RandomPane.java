package priv.wcj.sortview.pane;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * 随机数面板
 *
 * @author MysticalDream
 */
public class RandomPane extends BorderPane {

    private VBox vBox = new VBox();
    private TextField start = new TextField();
    private TextField end = new TextField();
    private HBox hBox = new HBox();
    private TextField count = new TextField();
    private Button button = new Button("生成");
    private TextArea textArea = new TextArea();

    {
        vBox.setSpacing(10);
    }

    {
        start.setPromptText("最小数字");
        end.setPromptText("最大数字");
        hBox.getChildren().addAll(start, end);
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);
        count.setPromptText("生成数字数量");
        textArea.setWrapText(true);
        textArea.setPromptText("结果");
        textArea.setMinHeight(200);
        vBox.getChildren().addAll(hBox, count, button, textArea);
        vBox.setAlignment(Pos.BASELINE_CENTER);
    }

    {
        button.setOnAction(e -> {
            int st = Integer.parseInt(start.getText());
            int ed = Integer.parseInt(end.getText());
            if (st > ed) {
                st = new int[]{ed, ed = st}[0];
            }
            int cnt = Integer.parseInt(count.getText());
            cnt = cnt > 0 ? cnt : 0;
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < cnt; i++) {
                stringBuilder.append(((int) (Math.random() * (ed - st + 1)) + st) + " ");
            }
            textArea.setText(stringBuilder.toString());
        });
    }

    public RandomPane() {
        this.setCenter(vBox);
    }
}
