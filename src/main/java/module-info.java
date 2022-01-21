module com.example.antiplagiat {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.antiplagiat to javafx.fxml;
    exports com.example.antiplagiat;
}