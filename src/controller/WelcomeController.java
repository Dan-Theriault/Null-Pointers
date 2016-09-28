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

    public void setApp(MainFXApplication application){
        this.mainApplication = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onLoginButtonPressed() throws Exception {
        mainApplication.gotoLogin();
    }



}