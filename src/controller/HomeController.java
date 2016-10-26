package controller;

import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Database;
import model.SourceReport;
import netscape.javascript.JSObject;

import java.net.URL;
import java.util.List;
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
    private boolean loaded = false;

//    @FXML
//    private void onMouseMoved() {
//
//
//        if (!loaded) {
//            loaded = true;
//            List<SourceReport> reports = mainApplication.db.getReports();
//            GeocodingService geocodingService = new GeocodingService();
//
//            for (SourceReport report : reports) {
//                String temp = report.getLocation();
//                System.out.println(temp);
//                geocodingService.geocode(report.getLocation(), (GeocodingResult[] results, GeocoderStatus status) -> {
//                    if (status == GeocoderStatus.ZERO_RESULTS) {
//                        //System.out.println("zero results");
//                        return;
//                    } else {
//                        LatLong tempLatLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
//                        report.setLatLong(tempLatLong);
//                        System.out.println(tempLatLong);
//                    }
//                });
//
//            }
//        }
//
//    }


}
