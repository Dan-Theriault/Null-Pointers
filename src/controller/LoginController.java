package controller;

import javafx.fxml.FXML;
import fxapp.MainFXApplication;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.User;

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

    /**
     * sets the main application for the controller
     * @param application the application to set
     */
    public void setApp(MainFXApplication application){
        this.mainApplication = application;
    }

    /**
     * initializes the controller
     * @param location the url location
     * @param resources the bundle of resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * handler for ok button press
     * @throws Exception the exception to be thrown
     */
    @FXML
    private void onOkButtonPressed() throws Exception {
        Boolean isLoginValid = mainApplication.db.logIn(userField.getText(),
                passField.getText());
        if (isLoginValid) {
             //mainApplication.db.setGlobalUser(mainApplication.db.findUser(userField.getText()));

            User temp = mainApplication.db.findUser(userField.getText());

            mainApplication.db.setGlobalUser(temp);

            System.out.println(temp);

            mainApplication.gotoHome();
        } else {
            responseField.setText("Invalid Login Credentials");
        }

    }

    /**
     * handler for cancel button press
     * @throws Exception the exception to be thrown
     */
    @FXML
    private void onCancelButtonPressed() throws Exception {
        mainApplication.gotoWelcome();
    }

}
