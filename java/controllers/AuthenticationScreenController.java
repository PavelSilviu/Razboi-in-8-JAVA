package controllers;

import databasemanipulation.Database;
import databasemanipulation.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class AuthenticationScreenController {
    @FXML
    PasswordField passField;
    @FXML
    TextField usernameField;
    @FXML
    Text formErrorMessage;

    public void sendAuthForm(MouseEvent event) throws IOException {
        String pass = null;
        try {
            var users = new UserDAO();
            //users.initUserDB();
//            users.create(1, "Silviu", 235);
//            users.create(2, "Mirela", 223);
            pass = users.findPassById(users.findByName(usernameField.getText()));
            Database.getConnection().commit();
            //Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println("eroare la logare");
            //e.printStackTrace();
        }
        if (pass.equals(passField.getText())) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/PvpScreen.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
            stage.show();
        }
        else{
            formErrorMessage.setText("User inexistent sau parola gresita! Va rugam sa incercati din nou.");
        }
    }

    public void switchToRegister(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/RegistrationScreen.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }

}
