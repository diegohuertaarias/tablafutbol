module com.empresa.tablafutbol {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.empresa.tablafutbol to javafx.fxml;
    exports com.empresa.tablafutbol;
}