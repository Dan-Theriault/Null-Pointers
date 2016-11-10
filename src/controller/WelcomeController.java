package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import fxapp.MainFXApplication;

import java.net.URL;
import java.util.ResourceBundle;

/** Controller class for the welcome page
 *
 * Created by nickhutchinson on 9/27/16.
 */
public class WelcomeController implements Initializable{

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
     * handler for login button press
     * @throws Exception the exception to be thrown
     */
    @FXML
    private void onLoginButtonPressed() throws Exception {
        mainApplication.gotoLogin();
    }

    /**
     * handler for register button press
     * @throws Exception the exception to be thrown
     */
    @FXML
    private void onRegisterButtonPressed() throws Exception {
        mainApplication.gotoNewUserRegistration();
    }
}