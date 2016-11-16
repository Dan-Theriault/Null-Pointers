package tests;

import model.AccountType;
import model.Database;
import model.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.bson.Document;


/**
 * Test class for {@link model.Database#saveWaterPurityReport(
 * String, String, String, String, String, String, String)}.
 *
 * @author Amethyst Sanders
 * @version 1.0
 */

public class saveWaterPurityReportTests {

    /**
     * Test data
     */
    private String name;
    private String date;
    private String reportNum;
    private String location;
    private String waterCondition;
    private String virus;
    private String contaminant;
    private String incorrect;
    Database test = new Database();
    private Document testReport1;
    private Document testReport2;
    private static final User testUserUser = new User("Mr.", "Test User",
    private static final User testUserManager = new User("Mr.", "Test User",


    /**
     * make the data to be tested
     */
    @Before
    public void setUp() {
        name = "user";
        date = "2016/11/16 11:40:00";
        reportNum = "test";
        location = "earth";
        waterCondition = "Unsafe";
        virus = "1000";
        contaminant = "6000";
        incorrect = "";
        testReport1 = new Document("reporterName", name)
                .append("reportDate", date)
                .append("reportNumber", reportNum)
                .append("reportLocation", location)
                .append("waterCondition", waterCondition)
                .append("virusPPM", virus)
                .append("contaminantPPM", contaminant);
        testReport2 = new Document("reporterName", name)
                .append("reportDate", date)
                .append("reportNumber", reportNum)
                .append("reportLocation", incorrect)
                .append("waterCondition", waterCondition)
                .append("virusPPM", virus)
                .append("contaminantPPM", contaminant);
        test.addUser(testUserUser);
        test.addUser(testUserManager);

    }

    /**
     * Test that the purity report is added to the database successfully
     *
     */
    @Test
    public void testadded() {
        assertFalse(test.saveWaterPurityReport(name, date, reportNum, location, incorrect,
                virus, contaminant));
        test.setGlobalUser(testUserManager);
        assertTrue(test.saveWaterPurityReport(name, date, reportNum, location, waterCondition,
                virus, contaminant));
        test.setGlobalUser(testUserUser);
        assertTrue(test.saveWaterPurityReport(name, date, reportNum, location, waterCondition,
                virus, contaminant));
    }

    @After
    public void cleanup() {
        test.deleteUser(testUserManager.getUsername());;
        test.deleteUser(testUserUser.getUsername());

}