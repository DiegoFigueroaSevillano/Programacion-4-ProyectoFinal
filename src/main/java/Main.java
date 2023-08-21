import View.Pages.LogIn;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();

        LogIn logIn = new LogIn(root, stage);

        Scene currentScene = logIn.getLogInScene();

        stage.setWidth(800);
        stage.setHeight(700);
        stage.setMinWidth(800);
        stage.setMinHeight(700);
        stage.setResizable(true);
        stage.setScene(currentScene);
        stage.show();
    }

}