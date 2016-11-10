package controller;


import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by iivcv on 11/1/2016.
 */
public class HistoricalReportEntryController implements Initializable{
    @FXML
    private TextField locationField;

    @FXML
    private ComboBox impurityBox;

    @FXML
    private ComboBox yearBox;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    private boolean loaded = false;

    private MainFXApplication mainApplication;

    ObservableList<String> impurities =
            FXCollections.observableArrayList(
                    "Virus",
                    "Contaminant"
            );

    ObservableList<Integer> years =
            FXCollections.observableArrayList(
                    2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019
            );

    /**
     * sets the mainApplication variable
     * @param application the main application
     */
    public void setApp(MainFXApplication application) {
        this.mainApplication = application;
    }


    /**
     * initializes the controller
     * @param location the url location
     * @param resources the bundle of resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        impurityBox.setItems(impurities);

        yearBox.setItems(years);
    }

    /**
     * handler for mouse moved
     * @throws Exception the exception that will be thrown
     */
    @FXML
    public void onMouseMoved() throws Exception {
        if (!loaded) {
            loaded = true;
        }
    }

    /**
     * handler for back button press
     * @throws Exception the exception that will be thrown
     */
    @FXML
    public void cancelButtonPressed() throws Exception {

        mainApplication.gotoHome();
    }

}
