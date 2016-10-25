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


public class MapViewController implements Initializable, MapComponentInitializedListener {

        @FXML
        private Button homeButton;

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

        @Override
        public void initialize(URL url, ResourceBundle rb) {
            mapView.addMapInializedListener(this);
        }

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


            //Add markers to the map
            for (SourceReport report : reports) {
                geocodingService.geocode(report.getLocation(), (GeocodingResult[] results, GeocoderStatus status) -> {
                    if( status == GeocoderStatus.ZERO_RESULTS) {
                        return;
                    } else {
                        location[0] = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                    }
                });
                LatLong reportLocation = location[0];

                MarkerOptions opts = new MarkerOptions();
                opts.position(reportLocation);
                Marker reportMarker = new Marker(opts);
                map.addMarker(reportMarker);
            }
        }
}
