package View.Pages;

import View.Components.Header;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Home {

    private final Scene homeScene;
    private VBox home;
    private Stage stage;
    private Header header;
    private StackPane ticketSection;

    public Home(Group root, Stage stage) {
        this.stage = stage;
        this.stage.setTitle("HOME -");
        this.homeScene = new Scene(root);

        this.header = new Header(stage);
        createHome(homeScene);

        root.getChildren().add(home);
    }

    private void createHome(Scene scene) {
        createTicketSection(scene);

        home = new VBox(0);
        home.prefHeightProperty().bind(stage.heightProperty());
        home.prefWidthProperty().bind(stage.widthProperty());
        home.setAlignment(Pos.CENTER);
        home.layoutXProperty().bind(scene.widthProperty().subtract(home.widthProperty()).divide(2));
        home.layoutYProperty().bind(scene.heightProperty().subtract(home.heightProperty()).divide(2));
        home.getChildren().addAll(header.getHeader(), ticketSection);
    }

    private void createTicketSection(Scene scene) {
        Image homeCover = new Image("img/Covers/home-cover.jpg");

        BackgroundImage homeBackground = new BackgroundImage(
                homeCover,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

        Background homeBG = new Background(homeBackground);

        ticketSection = new StackPane();
        ticketSection.prefWidthProperty().bind(scene.widthProperty());
        ticketSection.prefHeightProperty().bind(scene.heightProperty().subtract(100));
        ticketSection.setBackground(homeBG);
    }

    public Scene getHomeScene() {
        return homeScene;
    }
}
