package View.Pages;

import View.Components.Header;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Home {

    private final Scene homeScene;
    private VBox home;
    private Stage stage;
    private Header header;

    public Home(Group root, Stage stage) {
        this.stage = stage;
        this.stage.setTitle("HOME -");
        this.homeScene = new Scene(root);

        this.header = new Header();
        createHome(homeScene);

        root.getChildren().add(home);
    }

    private void createHome(Scene scene) {
        home = new VBox(20);
        home.prefHeightProperty().bind(stage.heightProperty());
        home.prefWidthProperty().bind(stage.widthProperty());

        home.setPadding(new Insets(60, 40, 60, 40));
        home.setAlignment(Pos.CENTER);
        home.layoutXProperty().bind(scene.widthProperty().subtract(home.widthProperty()).divide(2));
        home.layoutYProperty().bind(scene.heightProperty().subtract(home.heightProperty()).divide(2));
    }

    public Scene getHomeScene() {
        return homeScene;
    }
}
