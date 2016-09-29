package controller;

import fxapp.MainFXApplication;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by nickhutchinson on 9/28/16.
 */
public class RegisteredUsersController implements Initializable {

    private MainFXApplication mainApplication;

    public void setApp(MainFXApplication application) {
        this.mainApplication = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}