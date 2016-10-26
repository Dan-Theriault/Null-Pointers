package controller;

/**
 * Created by dtheriault3 on 10/25/16.
 */

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.Database;
import model.SourceReport;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import netscape.javascript.JSObject;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;


public class MapViewController implements Initializable, MapComponentInitializedListener {



        @FXML
        private Button homeButton;

    /**
     * the handler for the home button being pressed
     * @throws Exception
     */
    @FXML
        private void homeButtonPressed() throws Exception {
            mainApplication.gotoHome();
        }

        @FXML
        private GoogleMapView mapView;

        private GoogleMap map;

        private MainFXApplication mainApplication;
        public void setApp(MainFXApplication application) {
        this.mainApplication = application;
    }

        Database db = new Database();
        List<SourceReport> reports = db.getReports();

    /**
     * initializes the view for the page
     * @param url the url for the page
     * @param rb the resource bundle for the page
     */
    @Override
        public void initialize(URL url, ResourceBundle rb) {
            mapView.addMapInializedListener(this);
        }

    /**
     * initializes the map and the markers
     */
    @Override
        public void mapInitialized() {


            //Set the initial properties of the map.
            MapOptions mapOptions = new MapOptions();

            mapOptions.center(new LatLong(33.778463, -84.398881)) // coordinates for Georgia Tech
                    .mapType(MapTypeIdEnum.ROADMAP)
                    .overviewMapControl(false)
                    .panControl(true)
                    .rotateControl(true)
                    .scaleControl(true)
                    .streetViewControl(false)
                    .zoomControl(true)
                    .zoom(12);

            map = mapView.createMap(mapOptions);
            GeocodingService geocodingService = new GeocodingService();
            final LatLong[] location = {new LatLong(0, 0)};




            for (SourceReport report : reports) {
                System.out.println("for source report:");
                geocodingService.geocode(report.getLocation(), (GeocodingResult[] results, GeocoderStatus status) -> {
                    if( status == GeocoderStatus.ZERO_RESULTS) {
                        System.out.println("zero results");
                        return;
                    } else {
                        System.out.println("location");
                        location[0] = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                        LatLong temp = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                        System.out.println(results);
                        MarkerOptions opts = new MarkerOptions();
                        opts.position(temp);
                        Marker reportMarker = new Marker(opts);

                        map.addUIEventHandler(reportMarker,
                                UIEventType.click,
                                (JSObject obj) -> {
                                    InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                                    infoWindowOptions.content("<h2>" + report.getLocation() + "<h2>"
                                            + "Date: " + report.getDate() + "<br>"
                                            + "Type: " + report.getType() + "<br>"
                                            + "Condition" + report.getType() + "<br>");

                                    InfoWindow window = new InfoWindow(infoWindowOptions);
                                    window.open(map, reportMarker);
                                });

                        map.addMarker(reportMarker);

                    }
                });

            }

        }
}
