module com.example.programacion4proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;

    opens com.example.programacion4proyectofinal to javafx.fxml;
    exports com.example.programacion4proyectofinal;
    opens com.example.programacion4proyectofinal.StagesAndLogicTest to javafx.fxml;
    exports com.example.programacion4proyectofinal.StagesAndLogicTest;
}