package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

/** Controller for the home view.
 *
 * Created by dtheriault3 on 9/25/16.
 */
public class HomeController implements Initializable{

    private MainFXApplication mainApplication;

    @FXML
    private Label statusLabel;

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
     * @param resources the bundle of resources to initialize
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    /**
     * handler for logout button pressed
     * @throws Exception the exception to be thrown
     */
    @FXML
    private void handleLogoutPressed() throws Exception {
        mainApplication.gotoWelcome();
    }

    /**
     * handler for users button pressed
     * @throws Exception the exception to be thrown
     */
    @FXML
    private void onUsersButtonPressed() throws Exception {
        mainApplication.gotoUsersList();
    }

    /**
     * handler for requests button pressed
     * @throws Exception the exception to be thrown
     */
    @FXML
    private void onRequestsButtonPressed() throws Exception {
        mainApplication.gotoRequestsList();
    }

    /**
     * handler for profile button pressed
     * @throws Exception the exception to be thrown
     */
    @FXML
    private void handleProfilePressed() throws Exception {

        mainApplication.gotoProfile();

    }

    /**
     * handler for create report button pressed
     * @throws Exception the exception to be thrown
     */
    @FXML
    private void handleCreateReportPressed() throws Exception {

        mainApplication.goToCreateReport();

    }

    /**
     * handler for the "Report Map" button
     * @throws Exception the exception to be thrown
     */
    @FXML
    private void handleReportMapPressed() throws Exception {
        mainApplication.goToMapView();
    }


    /**
     * handler for view source reports button pressed
     * @throws Exception the exception to be thrown
     */
    @FXML
    private void viewSourceReports() throws Exception {

        mainApplication.goToViewSourceReports();

    }
    private boolean loaded = false;

    /**
     * handler for new purity report press
     * @throws Exception the exception to be thrown
     */
    @FXML
    private void handleNewPurityReport() throws Exception {

        String accountType = mainApplication.db.getGlobalUser().getAccountType().getAccountTypeValue();

        if (accountType.equals("ADMIN") || accountType.equals("WORKER") || accountType.equals("MANAGER")) {

            mainApplication.goToNewPurityReport();

        } else {

            statusLabel.setText("USER access denied");

        }

    }

    /**
     * handler for viewing purity reports
     * @throws Exception the exception to be thrown
     */
    @FXML
    private void viewPurityReports() throws Exception {

        mainApplication.goToViewPurityReports();

    }

    /**
     * handler for graph button press
     * @throws Exception the exception to be thrown
     */
    @FXML
    private void graphButtonPress() throws Exception {


        String accountType = mainApplication.db.getGlobalUser().getAccountType().getAccountTypeValue();

        if (accountType.equals("MANAGER")) {

            mainApplication.goToPurityGraph();

        } else {

            statusLabel.setText("USER access denied");

        }

    }


}
