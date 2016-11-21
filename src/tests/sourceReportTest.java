package tests;

import model.Database;
import org.junit.Test;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by iivcv on 11/15/2016.
 */
public class sourceReportTest {

    private Database db;


    private int pReports1;
    private int pReports2;
    private int pReports3;

    public void initialize() {
        db = new Database();

        pReports1 = db.getNewPurityReportNumber();
        db.saveWaterPurityReport("name", "11/15/2016", "1", "lat:15, lng:15", "good", "12", "25");
        pReports2 = db.getNewPurityReportNumber();
        db.saveWaterPurityReport("name", "11/15/2016", "1", "lat:15, lng:15", "good", "12", "25");
        pReports3 = db.getNewPurityReportNumber();
    }

    @Test
    public void compareNumbers() {
        initialize();
        assertEquals(pReports1 - pReports2, 1);
    }

    @Test
    public void ensureUnequal() {
        initialize();
        assertFalse(pReports1 != pReports3);
    }

}
