package controller;

import fxapp.MainFXApplication;
import javafx.collections.ObservableArray;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.net.URL;
import java.util.ResourceBundle;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

    private boolean loaded = false;

    private String reporterName;
    private String dateAndTime;
    private String waterType;
    private String waterCondition;
    private String waterLocation;
    private String reportNumber;


    private MainFXApplication mainApplication;

    ObservableList<String> waterTypes =
            FXCollections.observableArrayList(
                    "Bottled",
                    "Well",
                    "Stream",
                    "Lake",
                    "Spring",
                    "Other"
            );


    ObservableList<String> waterConditions =
            FXCollections.observableArrayList(
                    "Waste",
                    "Treatable-Clear",
                    "Treatable-Muddy",
                    "Potable"
            );

    /**
     * sets the mainApplication variable
     * @param application the main application
     */
    public void setApp(MainFXApplication application) {
        this.mainApplication = application;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        waterTypeComboBox.setItems(waterTypes);

        waterConditionComboBox.setItems(waterConditions);

    }

    @FXML
    public void backButtonPressed() throws Exception {

        mainApplication.gotoHome();

    }

    @FXML
    public void onMouseMoved() throws Exception {
        if (!loaded) {
            loaded = true;
            reporterName = mainApplication.db.getGlobalUser().getName();
            reporterNameLabel.setText(reporterName);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            dateAndTime = dateFormat.format(date);
            dateAndTimeLabel.setText(dateAndTime);

        }

    }

    @FXML
    public void onSaveReportPressed() throws Exception {

        String waterType = (String) waterTypeComboBox.getValue();
        String waterConditon = (String) waterConditionComboBox.getValue();

        mainApplication.db.saveWaterSourceReport(reporterName, dateAndTime, "1", locationTextField.getText(), waterType, waterConditon);
        mainApplication.gotoHome();
        //System.out.println(waterTypeComboBox.getValue());
    }
}
