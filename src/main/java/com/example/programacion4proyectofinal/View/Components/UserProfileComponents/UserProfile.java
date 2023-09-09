package com.example.programacion4proyectofinal.View.Components.UserProfileComponents;

import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class UserProfile implements Initializable {
    @FXML
    private Label CILabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private Label fullnameLabel;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Circle profileAvatarCircle;

    @FXML
    private ScrollPane scrollPaneHistory;
    private BackgroundGenerator backgroundGenerator = new BackgroundGenerator();


    private void loadInformationFlights() {
        VBox mainContainer = new VBox();
        mainContainer.setSpacing(5);
        mainContainer.setPadding(new Insets(2,0,0,0));

        for (int i = 1; i <= 30; i++) {
            String city = "Bogota - Colombia";
            String date = "10/10/2020 - 10:10 - 10:10";

            FlightPane flightPane = new FlightPane();
            Pane pane = flightPane.createContentInformationFlight("20202020", city, date, 680, 650, mainContainer);

            mainContainer.getChildren().add(pane);
        }
        scrollPaneHistory.setStyle("-fx-background-color: transparent;");

        scrollPaneHistory.setContent(mainContainer);
        scrollPaneHistory.setFitToWidth(true);
        scrollPaneHistory.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }


    private void initProfileAvatarImage() {
        Image avatarImage = new Image("/com/example/programacion4proyectofinal/Views/Images/profile-avatar.png");
        profileAvatarCircle.setFill(new ImagePattern(avatarImage));
    }

    private void initBackgroundImage() {
        Image img = new Image("/com/example/programacion4proyectofinal/Views/Images/background-profile.jpg");
        mainAnchorPane.setBackground(backgroundGenerator.createBackgroundImage(img.getUrl()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initBackgroundImage();
        initProfileAvatarImage();
        loadInformationFlights();
    }
}
