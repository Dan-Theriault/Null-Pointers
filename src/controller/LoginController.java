package controller;

import javafx.fxml.FXML;
import fxapp.MainFXApplication;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/** Controller class for the login page.
 *
 * Created by nickhutchinson on 9/27/16.
 */
public class LoginController implements Initializable{

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passField;

    @FXML
    private TextArea responseField;

    private MainFXApplication mainApplication;

    public void setApp(MainFXApplication application){
        this.mainApplication = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onOkButtonPressed() throws Exception {
        Boolean isLoginValid = mainApplication.db.logIn(userField.getText(),
                passField.getText());
        if (isLoginValid) {
            mainApplication.gotoHome();
        } else {
            responseField.setText("Invalid Login Credentials");
        }

    }

    @FXML
    private void onCancelButtonClicked() throws Exception {
        mainApplication.gotoWelcome();
    }

}
