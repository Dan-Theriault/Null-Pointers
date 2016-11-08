package model;

import com.lynden.gmapsfx.javascript.object.LatLong;

/**
 * Created by aaronwasserman on 11/2/16.
 */
public class PurityReport {

    private String reporter;
    private String date;
    private String reportNumber;
    private String location;
    private String condition;
    private String contaminant;
    private String virus;


    public PurityReport(String reporter, String date, String reportNumber, String location, String condition, String contaminant, String virus) {

        this.reporter = reporter;
        this.date = date;
        this.reportNumber = reportNumber;
        this.location = location;
        this.condition = condition;
        this.contaminant = contaminant;
        this.virus = virus;

    }

    public String getReporter() {return reporter;}

    public String getDate() {return date;}

    public String getReportNumber() {return reportNumber;}

    public String getLocation() {return location;}

    public String getCondition() {return condition;}

    public String getContaminant() {return contaminant;}

    public String getVirus() {return virus;}

}
