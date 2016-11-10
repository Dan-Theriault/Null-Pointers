package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.PurityReport;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by aaronwasserman on 11/2/16.
 */
public class ViewPurityReportsController implements Initializable {

    @FXML
    private Label workerLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label reportNumLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label conditionLabel;

    @FXML
    private Label virusLabel;

    @FXML
    private Label contaminantLabel;

    @FXML
    private ListView reportList;

    private int currentIndex;

    private boolean loaded = false;

    private ArrayList<PurityReport> reports;

    private MainFXApplication mainApplication;

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
     * handler for back button press
     * @throws Exception the exception to be thrown
     */
    @FXML
    private void backPressed() throws Exception {

        mainApplication.gotoHome();

    }

    /**
     * handler for mouse move
     */
    @FXML
    private void onMouseMoved() {
        if (!loaded) {
            loaded = true;
            ArrayList<String> locations = new ArrayList<>();
            reports = mainApplication.db.getPurityReports();
            locations.addAll(reports.stream().map(PurityReport::getLocation).collect(Collectors.toList()));
            ObservableList<String> items = FXCollections.observableArrayList(locations);
            reportList.setItems(items);
        }
    }

    /**
     * handler for list view click
     */
    @FXML
    private void onListViewClicked() {
        int newIndex = reportList.getSelectionModel().getSelectedIndex();
        if (newIndex != -1 || newIndex != currentIndex) {
            currentIndex = newIndex;
            PurityReport report = reports.get(currentIndex);

            if (report.getReporter() != null) {
                workerLabel.setText(report.getReporter());
            }
            if (report.getDate() != null) {
                dateLabel.setText(report.getDate());
            }
            if (report.getReportNumber() != null) {
                reportNumLabel.setText(report.getReportNumber());
            }
            if (report.getLocation() != null) {
                locationLabel.setText(report.getLocation());
            }
            if (report.getCondition() != null) {
                conditionLabel.setText(report.getCondition());
            }
            if (report.getVirus() != null) {
                virusLabel.setText(report.getVirus());
            }
            if (report.getContaminant() != null) {
                contaminantLabel.setText(report.getContaminant());
            }
        }
    }

}
