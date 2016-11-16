package tests;

/**
 * test class for
 * Created by dtheriault3 on 11/16/16.
 */

import model.AccountType;
import model.Database;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class updateUserTests {

    private final Database db = new Database();

    private static final User testUserAdmin = new User("Mr.", "Test User",
            "TestuserAdmin123", AccountType.ADMIN, "testuser@sample.com",
            "123 Test Rd", "password123Admin");
    private static final User testUserManager = new User("Mr.", "Test User",
            "TestuserManager123", AccountType.MANAGER, "testuser@sample.com",
            "123 Test Rd", "password123Manager");
    private static final User testUserWorker = new User("Mr.", "Test User",
            "TestuserWorker123", AccountType.WORKER, "testuser@sample.com",
            "123 Test Rd", "password123Worker");
    private static final User testUserUser = new User("Mr.", "Test User",
            "TestuserUser123", AccountType.USER, "testuser@sample.com",
            "123 Test Rd", "password123User");

    private static final User testUserOutOfDB = new User("Mrs.", "Test User",
            "TestuserUser123", AccountType.USER, "testuser@sample.com",
            "123 Test Rd", "password123User");

    @Before
    public void setup() {

        db.addUser(testUserAdmin);
        db.addUser(testUserManager);
        db.addUser(testUserWorker);
        db.addUser(testUserUser);
    }

    @Test
    public void adminSuccess() {
        db.setGlobalUser(testUserAdmin);
        assertTrue(db.updateUser("Mrs.", "Quiz User", "quizuser@sample.com", "8 Test Dr."));
    }

    @Test
    public void managerSuccess() {
        db.setGlobalUser(testUserManager);
        assertTrue(db.updateUser("Mrs.", "Bowl Manager", "bowler@sample.com", "2 Test Dr."));
    }


    @Test
    public void workerSuccess() {
        db.setGlobalUser(testUserWorker);
        assertTrue(db.updateUser("Fr.", "Dissertation", "smart@sample.com", "345 Town Way"));
    }

    @Test
    public void userSuccess() {
        db.setGlobalUser(testUserUser);
        assertTrue(db.updateUser("Ms.", "Homework", "rip@sample.com", "8 Test Dr."));
    }

    @Test
    public void nullUser() {
        db.setGlobalUser(null);
        assertTrue(!db.updateUser("fdfafa", "dfafda", "hrhrh", "egtatattttt"));
    }

    @Test
    public void userNotInDB() {
        db.setGlobalUser(testUserOutOfDB);
        assertTrue(!db.updateUser("eh.", "meh", "some sort of email", "some sort of address or something"));
    }

    @After
    public void cleanup() {
        db.deleteUser(testUserAdmin.getUsername());
        db.deleteUser(testUserManager.getUsername());
        db.deleteUser(testUserWorker.getUsername());
        db.deleteUser(testUserUser.getUsername());
    }
}
