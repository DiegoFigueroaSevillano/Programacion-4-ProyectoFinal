package Utils;

import javafx.stage.Stage;

public class ChangePropertiesStage {

    public void changeSizeStage(int width, int height, Stage stage) {
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setMinWidth(width);
        stage.setMinHeight(height);
    }

    public void changeMinSizeStage(int width, int height, Stage stage) {
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setMinWidth(width);
        stage.setMinHeight(height);
    }

}
