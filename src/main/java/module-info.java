module com.example.programacion4proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires org.json;

    opens com.example.programacion4proyectofinal to javafx.fxml;
    exports com.example.programacion4proyectofinal;
    exports com.example.programacion4proyectofinal.Model.Person to com.fasterxml.jackson.databind;
    exports com.example.programacion4proyectofinal.StagesAndLogicTest;
    opens com.example.programacion4proyectofinal.StagesAndLogicTest to javafx.fxml;
}