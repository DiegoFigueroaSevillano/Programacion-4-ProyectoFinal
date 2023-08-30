module com.example.programacion4proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;

    opens com.example.programacion4proyectofinal.Model.FileHandler to com.fasterxml.jackson.databind, com.fasterxml.jackson.core, com.fasterxml.jackson.annotation;
    exports com.example.programacion4proyectofinal.Model.FileHandler;

    opens com.example.programacion4proyectofinal.Model.DataStructure to com.fasterxml.jackson.databind, com.fasterxml.jackson.core, com.fasterxml.jackson.annotation;
    exports com.example.programacion4proyectofinal.Model.DataStructure;

    opens com.example.programacion4proyectofinal.Model.Person to com.fasterxml.jackson.databind, com.fasterxml.jackson.core, com.fasterxml.jackson.annotation;
    exports com.example.programacion4proyectofinal.Model.Person;

    opens com.example.programacion4proyectofinal to javafx.fxml;
    exports com.example.programacion4proyectofinal;
}