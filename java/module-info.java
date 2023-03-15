module com.project.supergame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;


    opens controllers to javafx.fxml;
    exports controllers;
    exports main;
    opens main to javafx.fxml;
    //opens images to javafx.fxml;
    //exports images;
}