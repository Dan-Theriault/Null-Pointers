package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.SourceReport;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by aaronwasserman on 10/18/16.
 */
public class ViewSourceReportsController implements Initializable {

    @FXML
    private ListView reportList;

    @FXML
    private Label reporterLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label reportNumberLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label conditionLabel;

    private MainFXApplication mainApplication;

    private ArrayList<SourceReport> reports;

    private int currentIndex;

    private boolean loaded = false;

    /**
     * sets the main application for the controller
     * @param application the application to be set
     */
    public void setApp(MainFXApplication application) {
        this.mainApplication = application;
    }

    /**
     * initialize the controller
     * @param location the url location
     * @param resources the bundle of resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * handler for list view click
     */
    @FXML
    private void onListViewClicked() {
        int newIndex = reportList.getSelectionModel().getSelectedIndex();
        if (newIndex != -1 || newIndex != currentIndex) {
            currentIndex = newIndex;
            SourceReport report = reports.get(currentIndex);

            if (report.getReporter() != null) {
                reporterLabel.setText(report.getReporter());
            }
            if (report.getDate() != null) {
                dateLabel.setText(report.getDate());
            }
            if (report.getReportNumber() != null) {
                reportNumberLabel.setText(report.getReportNumber());
            }
            if (report.getLocation() != null) {
                locationLabel.setText(report.getLocation());
            }
            if (report.getType() != null) {
                typeLabel.setText(report.getType());
            }
            if (report.getCondition() != null) {
                conditionLabel.setText(report.getCondition());
            }
        }
    }

    /**
     * handler for mouse moved
     */
    @FXML
    private void onMouseMoved() {
        if (!loaded) {
            loaded = true;
            ArrayList<String> locations = new ArrayList<>();
            reports = mainApplication.db.getReports();
            locations.addAll(reports.stream().map(SourceReport::getLocation).collect(Collectors.toList()));
            ObservableList<String> items = FXCollections.observableArrayList(locations);
            reportList.setItems(items);
        }
    }

    /**
     * handler for back button
     * @throws Exception the exception to be thrown
     */
    @FXML
    private void backButtonPressed() throws Exception {

        mainApplication.gotoHome();

    }

}
