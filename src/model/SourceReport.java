package model;

/**
 * Created by nickhutchinson on 10/18/16.
 */
public class SourceReport {
    private String reporter;
    private String date;
    private String reportNumber;
    private String location;
    private String type;
    private String condition;

    public SourceReport(String reporter, String date, String reportNumber,
                        String location, String type, String condition) {
        this.reporter = reporter;
        this.date = date;
        this.reportNumber = reportNumber;
        this.location = location;
        this.type = type;
        this.condition = condition;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
