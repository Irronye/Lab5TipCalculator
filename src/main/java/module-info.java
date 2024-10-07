module com.example.tipcalculator_jason_yi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.tipcalculator_jason_yi to javafx.fxml;
    exports com.example.tipcalculator_jason_yi;
}