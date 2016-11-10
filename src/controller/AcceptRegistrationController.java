package controller;

import fxapp.MainFXApplication;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by nickhutchinson on 9/28/16.
 */
public class AcceptRegistrationController implements Initializable {

    private MainFXApplication mainApplication;

    /**
     * sets the main application for the controller
     * @param application the application to be set
     */
    public void setApp(MainFXApplication application) {
        this.mainApplication = application;
    }

    /**
     * initializes the controller
     * @param location the url location
     * @param resources the bundle of resources to initialize
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}