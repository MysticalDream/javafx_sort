package priv.wcj.sortview.pane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import priv.wcj.sortview.manager.SceneManager;
import priv.wcj.sortview.manager.StageManager;
import priv.wcj.sortview.enums.SceneName;
import priv.wcj.sortview.enums.StageName;
import priv.wcj.sortview.strategy.Call;
import priv.wcj.sortview.strategy.SortStrategy;
import priv.wcj.sortview.strategy.SortStrategyFactory;


import java.util.function.UnaryOperator;

/**
 * 我的自定义面板
 *
 * @author MysticalDream
 */
public class MyPane extends BorderPane {

    //9 ，10，29，30，50，22，43，66
    private final int MAX_INPUT = 10;
    private ObservableList list = FXCollections.observableArrayList(SortStrategyFactory.getLists());
    SortStrategy selectedItem;
    private VBox vBox = new VBox();
    //1
    private Button randomButton = new Button("随机数生成器");
    private ToggleGroup toggleGroup = new ToggleGroup();
    private RadioButton button1 = new RadioButton("升序");
    private RadioButton button2 = new RadioButton("降序");
    private Label label = new Label("算法选择:");
    private ChoiceBox choiceBox = new ChoiceBox(list);
    private HBox hBox1 = new HBox(randomButton, button1, button2, label, choiceBox);
    //2
    private Button button = new Button("排序");
    private TextField textField = new TextField();
    private HBox hBox2 = new HBox(textField, button);
    //3
    private Label tip = new Label();
    //4
    private SplitPane splitPane = new SplitPane();
    private ScrollPane scrollPane1 = new ScrollPane();
    private ScrollPane scrollPane2 = new ScrollPane();
    private VBox resultBox = new VBox();
    private Label explain = new Label("思想介绍");

    //Top
    {
        vBox.getChildren().addAll(hBox1, tip, hBox2);
    }

    {
        tip.setTextFill(Color.RED);
        tip.setFont(Font.font(15));
    }

    {
        randomButton.setOnAction(e -> {
            StageManager.getStageByName(StageName.MY_STAGE).setScene(SceneManager.getScene(SceneName.RANDOM_SCENE));
            StageManager.showStage(StageName.MY_STAGE);
        });
        button1.setToggleGroup(toggleGroup);
        button2.setToggleGroup(toggleGroup);
        button1.setSelected(true);
        hBox1.setSpacing(20);
        hBox1.setAlignment(Pos.CENTER);
        HBox.setMargin(label, new Insets(0, 0, 0, 50));
        choiceBox.getSelectionModel().select(0);
        selectedItem = (SortStrategy) choiceBox.getSelectionModel().getSelectedItem();
        choiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selectedItem = (SortStrategy) newValue;
            }
        });

    }

    UnaryOperator<TextFormatter.Change> formatter1 = (t) -> {
        if (t.isContentChange()) {
            if (t.getControlNewText().length() == 0) {
                return t;
            }
            String controlNewText = t.getControlNewText();
            boolean b = controlNewText.matches("^([0-9 ,，])+$");
            if (!b) {
                return null;
            }
        }
        return t;
    };

    {
        textField.setPrefWidth(400);
        textField.setPromptText("输入一序列整数，以逗号或则空格分隔开");
        textField.setTextFormatter(new TextFormatter<>(formatter1));
        button.setCursor(Cursor.HAND);
        HBox.setMargin(button, new Insets(0, 0, 0, 100));
        button.setOnAction(new Sort());
    }


    private class Sort implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String text = textField.getText();
            //忽略异常输入
            int[] array = getArray(text, false);
            boolean option = button1.isSelected();
            resultBox.getChildren().clear();
            selectedItem.sort(array, option, new Call() {
                @Override
                public void add(int[] val, int index1, int index2) {
                    HBox hBox = new HBox();
                    hBox.getChildren().addAll(getLabels(val, index1, index2));
                    hBox.setSpacing(5);
                    hBox.setAlignment(Pos.CENTER);
                    resultBox.getChildren().add(hBox);
                }
            });
            explain.setText(selectedItem.getThought());

        }
    }

    private Label[] getLabels(int[] val, int index1, int index2) {
        Label[] labels = new Label[val.length];

        for (int i = 0, len = val.length; i < len; i++) {
            Label label = new Label();
            label.setText(val[i] + "");
            labels[i] = label;
            if (i == index1 || i == index2) {
                label.setStyle("-fx-font-size: 15px;-fx-background-color: red;-fx-alignment: center;");
            } else {
                label.setStyle("-fx-font-size: 15px;-fx-background-color: white;-fx-alignment: center;");
            }
            label.setPrefSize(-1, -1);
        }
        return labels;
    }

    /**
     * 获取整型字符串的数组形式
     *
     * @param line   数组字符串
     * @param option 是否在解析整数出现异常时将异常抛出，如果是true则抛出异常，否则忽略该异常即忽略这个异常值
     * @return 返回字符串分割好的整型数组
     */
    private int[] getArray(String line, boolean option) throws NumberFormatException {
        String[] split = line.split("[，, ]");
        int length = split.length;
        int[] a = new int[length];
        for (int i = 0, j = 0, len = length; j < len; i++, j++) {
            try {
                a[i] = Integer.parseInt(split[j]);
            } catch (NumberFormatException e) {
                if (option) {
                    throw new NumberFormatException();
                }
                //退一位到前一个有效数字位置
                i--;
                //新建一个长度比原来减1的数组
                int[] temp = new int[--length];
                //如果现在的有效数字位置大于等于0的话，将原来数组的值复制到temp数组
                if (i >= 0) {
                    System.arraycopy(a, 0, temp, 0, i + 1);
                }
                //将长度减一的数组temp赋值给a数组
                a = temp;
            }
        }
        return a;
    }

    //main
    {
        explain.setWrapText(true);
        explain.setStyle("-fx-font-size: 20px;-fx-text-fill:#4d4d4d;");
        resultBox.setSpacing(5);
        scrollPane1.setContent(resultBox);
        scrollPane1.setFitToWidth(true);
        scrollPane2.setFitToWidth(true);
        scrollPane2.setContent(explain);
        splitPane.getItems().addAll(scrollPane1, scrollPane2);
        splitPane.setDividerPositions(0.5);
    }

    public MyPane() {
        this.setTop(vBox);
        this.setCenter(splitPane);
    }
}
