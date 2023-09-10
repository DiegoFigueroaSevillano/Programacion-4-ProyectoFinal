package com.example.programacion4proyectofinal.Controller;

import com.example.programacion4proyectofinal.Model.FlightParser;
import com.example.programacion4proyectofinal.Utils.ViewUtils.BackgroundGenerator;
import com.example.programacion4proyectofinal.Utils.ViewUtils.GenerateFont;
import com.example.programacion4proyectofinal.View.Pages.FlightView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.SKY_BLUE;
import static com.example.programacion4proyectofinal.Utils.ViewUtils.Colors.SKY_BLUE_75;


/**
 * The FlightParser class represents flight data obtained from a JSON file.
 */
public class FlightController {

    private FlightView flightView;
    private ArrayList<FlightParser> flightList;
    private ObservableList<Button> flightComponents;
    private GenerateFont generateFont;
    private BackgroundGenerator backgroundGenerator;



    public FlightController(Group root, Stage stage) {
        this.flightList = obtainAllPassengers();
        this.generateFont = new GenerateFont();
        this.backgroundGenerator = new BackgroundGenerator();

        this.flightComponents = createFlightComponents(flightList);
        this.flightView = new FlightView(root, stage, flightComponents);
    }

    /**
     * Creates a list of flight components as Buttons.
     *
     * @param passengers The list of FlightParser objects.
     * @return An ObservableList of Buttons representing flight components.
     */
    private ObservableList<Button> createFlightComponents(ArrayList<FlightParser> passengers) {
        ObservableList<Button> passengersComponent = FXCollections.observableArrayList();
        for (FlightParser passenger : passengers) {
            passengersComponent.add(generateFight(passenger, passengersComponent));
        }
        return passengersComponent;
    }
    /**
     * Generates a Button representing a flight component.
     *
     * @param flightParser        The FlightParser object.
     * @param passengersComponents The list of existing passenger components.
     * @return A Button representing the flight component.
     */
    private Button generateFight(FlightParser flightParser, ObservableList<Button> passengersComponents) {
        Button button = new Button();
        button.setPrefWidth(2000);
        button.setStyle("-fx-background-radius: 15; -fx-border-radius: 15; -fx-background-color: #26C6DA75");
        GridPane gridPane = createGridPane(flightParser);

        Button addButton = generateButton("/com/example/programacion4proyectofinal/Icons/add-button.png", SKY_BLUE,40, 40);
        addButton.setAlignment(Pos.CENTER);
        addButton.setStyle("-fx-background-color: #0077A4; -fx-border-color: #005C8B; -fx-border-width: 2px; -fx-background-insets: 0; -fx-padding: 0;");

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("redirecciona a la otra pagina");
            }
        });

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("redirecciona a la otra pagina");
            }
        });
        HBox hbox = new HBox(gridPane , addButton);
        hbox.setSpacing(10);

        button.setGraphic(hbox);
        HBox.setHgrow(gridPane, Priority.ALWAYS);
        HBox.setHgrow(addButton, Priority.NEVER);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setVgrow(Priority.ALWAYS);
        gridPane.getRowConstraints().addAll(rowConstraints, rowConstraints);

        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(colConstraints, colConstraints, colConstraints, colConstraints, colConstraints);

        button.setGraphic(hbox);
        return button;
    }

    /**
     * Generates a Button with an image.
     *
     * @param pathImage    The path to the image resource.
     * @param color        The background color of the button.
     * @param imageWidth   The width of the image.
     * @param imageHeight  The height of the image.
     * @return A Button with an image.
     */

    private Button generateButton(String pathImage, String color, double imageWidth, double imageHeight) {
        BackgroundGenerator backgroundGenerator = new BackgroundGenerator();
        Image iconImage = new Image(getClass().getResourceAsStream(pathImage));
        ImageView icon = new ImageView(iconImage);
        icon.setFitWidth(imageWidth);
        icon.setFitHeight(imageHeight);

        Button button = new Button();
        button.setGraphic(icon);
        button.setPrefSize(40, 40);
        button.setMinSize(40, 40);
        button.setMaxSize(40, 40);
        button.setCursor(Cursor.HAND);
        return button;
    }


    /**
     * Creates a GridPane containing flight information.
     *
     * @param flightParser The FlightParser object containing flight data.
     * @return A GridPane with flight information.
     */
    private GridPane createGridPane(FlightParser flightParser){
        GridPane gridPane = new GridPane();
        gridPane.setHgap(50);
        gridPane.setVgap(5);

        Label departureLabel = new Label("Place Departure:");
        Label arrivalLabel = new Label("Place Arrival:");
        Label priceLabel = new Label("Price:");
        Label arrivalTimeLabel = new Label("Arrival Time:");
        Label departureTimeLabel = new Label("Departure Time:");
        Label airlineLabel = new Label("Airline:");


        Label departureValue = new Label(flightParser.getOrigin());
        Label arrivalValue = new Label(flightParser.getDestination());
        Label priceValue = new Label(String.valueOf(flightParser.getPrice()));
        Label arrivalTimeValue = new Label(flightParser.getArrivalDate());
        Label departureTimeValue = new Label(flightParser.getDepartureDate());
        Label airlineValue = new Label(flightParser.getAirline());


        departureLabel.setFont(generateFont.latoRegular(16));
        arrivalLabel.setFont(generateFont.latoRegular(16));
        priceLabel.setFont(generateFont.latoRegular(16));
        arrivalTimeLabel.setFont(generateFont.latoRegular(16));
        departureTimeLabel.setFont(generateFont.latoRegular(16));
        airlineLabel.setFont(generateFont.latoRegular(16));

        departureValue.setFont(generateFont.latoRegular(14));
        arrivalValue.setFont(generateFont.latoRegular(14));
        priceValue.setFont(generateFont.latoRegular(14));;
        arrivalTimeValue.setFont(generateFont.latoRegular(14));
        departureTimeValue.setFont(generateFont.latoRegular(14));
        airlineValue.setFont(generateFont.latoRegular(14));

        GridPane.setHalignment(departureLabel, HPos.CENTER);
        GridPane.setValignment(departureLabel, VPos.CENTER);
        GridPane.setHalignment(arrivalLabel, HPos.CENTER);
        GridPane.setValignment(arrivalLabel, VPos.CENTER);
        GridPane.setHalignment(priceLabel, HPos.CENTER);
        GridPane.setValignment(priceLabel, VPos.CENTER);
        GridPane.setHalignment(arrivalTimeLabel, HPos.CENTER);
        GridPane.setValignment(arrivalTimeLabel, VPos.CENTER);
        GridPane.setHalignment(departureTimeLabel, HPos.CENTER);
        GridPane.setValignment(departureTimeLabel, VPos.CENTER);
        GridPane.setHalignment(airlineLabel, HPos.CENTER);
        GridPane.setValignment(airlineLabel, VPos.CENTER);

        GridPane.setHalignment(departureValue, HPos.CENTER);
        GridPane.setValignment(departureValue, VPos.CENTER);
        GridPane.setHalignment(arrivalValue, HPos.CENTER);
        GridPane.setValignment(arrivalValue, VPos.CENTER);
        GridPane.setHalignment(priceValue, HPos.CENTER);
        GridPane.setValignment(priceValue, VPos.CENTER);
        GridPane.setHalignment(arrivalTimeValue, HPos.CENTER);
        GridPane.setValignment(arrivalTimeValue, VPos.CENTER);
        GridPane.setHalignment(departureTimeValue, HPos.CENTER);
        GridPane.setValignment(departureTimeValue, VPos.CENTER);
        GridPane.setHalignment(airlineValue, HPos.CENTER);
        GridPane.setValignment(airlineValue, VPos.CENTER);
        gridPane.addRow(0, airlineLabel,departureLabel,  departureTimeLabel, arrivalTimeLabel,arrivalLabel,priceLabel);
        gridPane.addRow(1,airlineValue, departureValue,  departureTimeValue,arrivalTimeValue,arrivalValue, priceValue );


        return gridPane;
    }

    /**
     * Obtains a list of flight passengers from a JSON file.
     *
     * @return An ArrayList of FlightParser objects representing passengers.
     */
    private ArrayList<FlightParser> obtainAllPassengers() {
        ArrayList<FlightParser> passengersList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("flights.json");
            JSONTokener jsonTokener = new JSONTokener(fileReader);
            JSONArray jsonArray = new JSONArray(jsonTokener);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject flightJson = jsonArray.getJSONObject(i);
                int id = flightJson.getInt("idFlight");
                String origin = flightJson.getString("origin");
                String destination = flightJson.getString("destination");
                int price = flightJson.getInt("price");
                String departureDate = flightJson.getString("departureDate");
                String arrivalDate = flightJson.getString("arrivalDate");
                String airline = flightJson.getString("airline");
                FlightParser flightParser = new FlightParser(id, origin, destination,airline, departureDate, arrivalDate , price);
                passengersList.add(flightParser);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return passengersList;
    }

    /**
     * Get the FlightView containing flight components.
     *
     * @return The FlightView object.
     */
    public FlightView getFlight() {
        return flightView;
    }
}
