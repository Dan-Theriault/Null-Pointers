package tests;

import model.AccountType;
import model.Database;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * @author Nick Hutchinson
 */
public class loginTest {

    private final Database db = new Database();

    private static final User testUserAdmin = new User("Mr.", "Test User",
            "TestuserAdmin123", AccountType.ADMIN, "testuser@sample.com",
            "123 Test Rd", "password123Admin");
    private static final User testUserManager = new User("Mr.", "Test User",
            "Testuser123Manager", AccountType.MANAGER, "testuser@sample.com",
            "123 Test Rd", "password123Manager");
    private static final User testUserWorker = new User("Mr.", "Test User",
            "Testuser123Worker", AccountType.WORKER, "testuser@sample.com",
            "123 Test Rd", "password123Worker");
    private static final User testUserUser = new User("Mr.", "Test User",
            "Testuser123User", AccountType.USER, "testuser@sample.com",
            "123 Test Rd", "password123User");


    @Before
    public void setup() {
        db.addUser(testUserAdmin);
        db.addUser(testUserManager);
        db.addUser(testUserWorker);
        db.addUser(testUserUser);
    }
    
    //Admin Tests
    @Test
    public void adminSuccess() {
        assertTrue(db.logIn(testUserAdmin.getUsername(), testUserAdmin.getPassword()));
    }
    
    @Test
    public void adminWrongPassword() {
        assertFalse(db.logIn(testUserAdmin.getUsername(), "password123admin"));
    }

    @Test
    public void adminWrongUserName() {
        assertFalse(db.logIn("testuseradmin123", testUserAdmin.getPassword()));
    }

    @Test
    public void adminWrongInfo() {
        assertFalse(db.logIn("testuseradmin123", "password123admin"));
    }

    //Manager Tests
    @Test
    public void managerSuccess() {
        assertTrue(db.logIn(testUserManager.getUsername(), testUserManager.getPassword()));
    }

    @Test
    public void managerWrongPassword() {
        assertFalse(db.logIn(testUserManager.getUsername(), "password123manager"));
    }

    @Test
    public void managerWrongUserName() {
        assertFalse(db.logIn("testusermanager123", testUserManager.getPassword()));
    }

    @Test
    public void managerWrongInfo() {
        assertFalse(db.logIn("testusermanager123", "password123manager"));
    }

    //Worker Tests
    @Test
    public void workerSuccess() {
        assertTrue(db.logIn(testUserWorker.getUsername(), testUserWorker.getPassword()));
    }

    @Test
    public void workerWrongPassword() {
        assertFalse(db.logIn(testUserWorker.getUsername(), "password123worker"));
    }

    @Test
    public void workerWrongUserName() {
        assertFalse(db.logIn("testuserworker123", testUserWorker.getPassword()));
    }

    @Test
    public void workerWrongInfo() {
        assertFalse(db.logIn("testuserworker123", "password123worker"));
    }

    //User Tests
    @Test
    public void userSuccess() {
        assertTrue(db.logIn(testUserUser.getUsername(), testUserUser.getPassword()));
    }

    @Test
    public void userWrongPassword() {
        assertFalse(db.logIn(testUserUser.getUsername(), "password123user"));
    }

    @Test
    public void userWrongUserName() {
        assertFalse(db.logIn("testuseruser123", testUserUser.getPassword()));
    }

    @Test
    public void userWrongInfo() {
        assertFalse(db.logIn("testuseruser123", "password123user"));
    }
    
    
    @After
    public void cleanup() {
        db.deleteUser(testUserAdmin.getUsername());
        db.deleteUser(testUserManager.getUsername());
        db.deleteUser(testUserWorker.getUsername());
        db.deleteUser(testUserUser.getUsername());
    }
}
