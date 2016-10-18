package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by aaronwasserman on 10/17/16.
 */
public class NewWaterReportController implements Initializable {

    @FXML
    private Label reporterNameLabel;

    @FXML
    private Label dateAndTimeLabel;

    @FXML
    private Label reportNumberLabel;

    @FXML
    private TextField locationTextField;

    @FXML
    private ComboBox waterTypeComboBox;

    @FXML
    private ComboBox waterConditionComboBox;


    private MainFXApplication mainApplication;

    /**
     * sets the mainApplication variable
     * @param application the main application
     */
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
