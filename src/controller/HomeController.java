package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/** Controller for the home view.
 *
 * Created by dtheriault3 on 9/25/16.
 */
public class HomeController implements Initializable{

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
     * @param resources the bundle of resources to initialize
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * handler for logout button pressed
     * @throws Exception
     */
    @FXML
    private void handleLogoutPressed() throws Exception {
        mainApplication.gotoWelcome();
    }

    /**
     * handler for users button pressed
     * @throws Exception
     */
    @FXML
    private void onUsersButtonPressed() throws Exception {
        mainApplication.gotoUsersList();
    }

    /**
     * handler for requests button pressed
     * @throws Exception
     */
    @FXML
    private void onRequestsButtonPressed() throws Exception {
        mainApplication.gotoRequestsList();
    }

    /**
     * handler for profile button pressed
     * @throws Exception
     */
    @FXML
    private void handleProfilePressed() throws Exception {

        mainApplication.gotoProfile();

    }

    /**
     * handler for create report button pressed
     * @throws Exception
     */
    @FXML
    private void handleCreateReportPressed() throws Exception {

        mainApplication.goToCreateReport();

    }

    /**
     * handler for the "Report Map" button
     * @throws Exception
     */
    @FXML
    private void handleReportMapPressed() throws Exception {
        mainApplication.goToMapView();
    }


    /**
     * handler for view source reports button pressed
     * @throws Exception
     */
    @FXML
    private void viewSourceReports() throws Exception {

        mainApplication.goToViewSourceReports();

    }
}
