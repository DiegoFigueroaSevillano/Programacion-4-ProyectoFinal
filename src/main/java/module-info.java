module com.example.programacion4proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens com.example.programacion4proyectofinal.Model.FileHandler to com.fasterxml.jackson.databind, com.fasterxml.jackson.core, com.fasterxml.jackson.annotation;
    exports com.example.programacion4proyectofinal.Model.FileHandler;

    opens com.example.programacion4proyectofinal.Model.DataStructure to com.fasterxml.jackson.databind, com.fasterxml.jackson.core, com.fasterxml.jackson.annotation;
    exports com.example.programacion4proyectofinal.Model.DataStructure;

    opens com.example.programacion4proyectofinal.Model.Person to com.fasterxml.jackson.databind, com.fasterxml.jackson.core, com.fasterxml.jackson.annotation;
    exports com.example.programacion4proyectofinal.Model.Person;

    opens com.example.programacion4proyectofinal to javafx.fxml;
    exports com.example.programacion4proyectofinal;
    opens com.example.programacion4proyectofinal.StagesAndLogicTest to javafx.fxml;
    exports com.example.programacion4proyectofinal.StagesAndLogicTest;
    exports com.example.programacion4proyectofinal.Model.FileHandler.Deserializer;
    opens com.example.programacion4proyectofinal.Model.FileHandler.Deserializer to com.fasterxml.jackson.annotation, com.fasterxml.jackson.core, com.fasterxml.jackson.databind;
    exports com.example.programacion4proyectofinal.Model.FileHandler.Writer;
    opens com.example.programacion4proyectofinal.Model.FileHandler.Writer to com.fasterxml.jackson.annotation, com.fasterxml.jackson.core, com.fasterxml.jackson.databind;
    exports com.example.programacion4proyectofinal.Model.UserFlightInfo to com.fasterxml.jackson.databind;
    exports com.example.programacion4proyectofinal.Utils.Generators.UserFlightInfoDataBase to com.fasterxml.jackson.databind;
    exports com.example.programacion4proyectofinal.Model.Flight to com.fasterxml.jackson.databind;
    exports com.example.programacion4proyectofinal.Utils.Generators.FlightDataBase to com.fasterxml.jackson.databind;

}