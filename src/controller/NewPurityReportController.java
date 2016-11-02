package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * Created by aaronwasserman on 11/1/16.
 */
public class NewPurityReportController implements Initializable {

    @FXML
    private Label workerNameLabel;

    @FXML
    private Label dateAndTimeLabel;

    @FXML
    private Label reportNumLabel;

    @FXML
    private TextField locationTextField;

    @FXML
    private ComboBox conditionComboBox;

    @FXML
    private TextField virusTextField;

    @FXML
    private TextField contaminantTextField;

    private String workerName;
    private String dateAndTime;

    ObservableList<String> waterTypes =
            FXCollections.observableArrayList(
                    "Safe",
                    "Treatable",
                    "Unsafe"
            );

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

        conditionComboBox.setItems(waterTypes);

    }

    @FXML
    public void goBack() throws Exception {

        mainApplication.gotoHome();

    }

    private boolean loaded = false;
    /**
     * handler for mouse moved
     * @throws Exception
     */
    @FXML
    public void onMouseMoved() throws Exception {
        if (!loaded) {
            loaded = true;
            workerName = mainApplication.db.getGlobalUser().getName();
            workerNameLabel.setText(workerName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            dateAndTime = dateFormat.format(date);
            dateAndTimeLabel.setText(dateAndTime);

            reportNumLabel.setText("temp");

        }

    }

    @FXML
    public void saveReport() throws Exception {

        String waterCondition = (String) conditionComboBox.getValue();


        if (locationTextField.getText() != "" && virusTextField.getText() != "" && contaminantTextField.getText() != "") {

            mainApplication.db.saveWaterPurityReport(workerName, dateAndTime, reportNumLabel.getText(), locationTextField.getText(), waterCondition, virusTextField.getText(), contaminantTextField.getText());
            mainApplication.gotoHome();

        }

    }

}
