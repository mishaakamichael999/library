module project.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;
    requires de.jensd.fx.glyphs.fontawesome;
    requires mysql.connector.j;

    opens project.library to javafx.fxml;
    exports project.library;
}