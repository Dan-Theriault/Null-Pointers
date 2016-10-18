package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by aaronwasserman on 10/18/16.
 */
public class ViewSourceReportsController implements Initializable {

    private MainFXApplication mainApplication;

    public void setApp(MainFXApplication application) {
        this.mainApplication = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void backButtonPressed() throws Exception {

        mainApplication.gotoHome();

    }

}
