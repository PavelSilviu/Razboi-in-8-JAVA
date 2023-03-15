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
import java.util.Random;

import static java.lang.Integer.parseInt;

public class RegistrationScreenController {
    @FXML
    PasswordField passField;
    @FXML
    TextField usernameField;
    @FXML
    Text formErrorMessage;

    public void sendRegForm(MouseEvent event) throws IOException {
        String pass = null;
        try {
            var users = new UserDAO();
            Random rand = new Random();
            if(users.findByName(usernameField.getText())!=0)
                formErrorMessage.setText("User deja existent! Va rugam sa incercati din nou.");
            else{
                users.create(rand.nextInt(1000), usernameField.getText(), parseInt(passField.getText()));
                Database.getConnection().commit();
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/PvpScreen.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.getScene().setRoot(root);
                stage.show();
            }

        } catch (SQLException e) {
            System.err.println("eroare la logare");
        }
    }
}
