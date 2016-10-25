package model;

import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by nickhutchinson on 9/21/16.
 *
 * Model for Mongo Database
 */
public class Database {

    /**
     * The database instance
     */
    private MongoDatabase db;

    private User globalUser;

    /**
     * Database constructor.
     */
    public Database() {
        MongoClient mongoClient = new MongoClient("georgiadelta.com" , 27017);
        db = mongoClient.getDatabase("NullPointers");
    }

    /**
     * Checks if the supplied user and password are valid.
     * @param user the username String
     * @param password the password String
     * @return true if the user is valid, false otherwise
     */
    public Boolean logIn(String user, String password) {
        FindIterable<Document> iterable = db.getCollection("users").find(
                new Document("username", user));
        final Boolean[] isTrue = new Boolean[1];

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                isTrue[0] = document.get("password").equals(password);
            }
        });

        if (isTrue[0] == null) {
            return false;
        }
        //return isTrue[0];
        if (isTrue[0] == true) {

            return true;

        } else {

            return false;

        }
    }

    /**
     * Performs a search of users based on the username identifer
     * @param username string holding user identifier
     * @return user object with username matching given username
     */
    public User findUser(String username) {

        FindIterable<Document> iterable = db.getCollection("users").find(new Document("username", username));
        final User[] userArray = new User[1];

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                User user = new User((String) document.get("title"),
                        (String) document.get("name"),
                        (String) document.get("username"),
                        AccountType.valueOf((String) document.get("accountType")),
                        (String) document.get("emailAddress"),
                        (String) document.get("homeAddress"),
                        (String) document.get("password"));
                userArray[0] = user;
            }
        });

        return userArray[0];
    }

    /**
     * Registers a new user to the database
     * @param name          real name of user
     * @param username      user identifier used by application
     * @param password      password required to login
     * @param accountType   account type, must be user, worker, manager, or admin
     */
    public void registerUser(String name, String username, String password,
            AccountType accountType) {

        MongoCollection<Document> registrations = db.getCollection("registrations");
        Document newUser = new Document("name", name)
                .append("username", username)
                .append("password", password)
                .append("accountType", accountType.getAccountTypeValue());

        registrations.insertOne(newUser);
    }

    /**
     * Approves a pending registration
     * @param name       real name of user
     * @param username   user identifier used by application
     * @param password   password required to login
     * @param accountType type of account, must be user, worker, manager, or admin. Controls permissions
     */
    public void confirmUser(String name, String username, String password, AccountType accountType) {

        System.out.println("confirm user");

        MongoCollection<Document> users = db.getCollection("users");
        Document newConfirmedUser = new Document("name", name)
                .append("username", username)
                .append("password", password)
                .append("accountType", accountType.getAccountTypeValue())
                .append("emailAddress", "example@gmail.com")
                .append("homeAddress", "564 Main Street")
                .append("title", "Mr.");

        users.insertOne(newConfirmedUser);

    }

    /**
     * Gets a list of all users of the application
     * @return ArrayList<User> of all confirmed users in database
     */
    public ArrayList<User> getUsers() {
        System.out.println("in get users");
        FindIterable<Document> iterable = db.getCollection("users").find();

        ArrayList<User> userList = new ArrayList<>();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                User user = new User((String) document.get("title"),
                        (String) document.get("name"),
                        (String) document.get("username"),
                        AccountType.valueOf((String) document.get("accountType")),
                        (String) document.get("emailAddress"),
                        (String) document.get("homeAddress"),
                        (String) document.get("password"));
                userList.add(user);
                //System.out.println(document);
            }
        });
        return userList;
    }

    /**
     * Get a list of all pending user registration requests
     * @return ArrayList<User> containing all pending users
     */
    public ArrayList<User> getRequests() {
        FindIterable<Document> iterable = db.getCollection("registrations").find();

        ArrayList<User> requestsList = new ArrayList<>();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                User user = new User(
                        (String) document.get("name"),
                        (String) document.get("username"),
                        AccountType.valueOf((String) document.get("accountType")),
                        (String) document.get("password"));
                requestsList.add(user);
            }
        });
        return requestsList;
    }

    /**
     * Return a list of all water source reports
     * @return ArrayList<SourceReport> containing all source reports in database
     */
    public ArrayList<SourceReport> getReports() {
        FindIterable<Document> iterable = db.getCollection("sourceReports").find();

        ArrayList<SourceReport> reportList = new ArrayList<>();
        iterable.forEach(new Block<Document>() {

            @Override
            public void apply(Document document) {

                SourceReport report = new SourceReport(
                        (String) document.get("reporterName"),
                        (String) document.get("reportDate"),
                        (String) document.get("reportNumber"),
                        (String) document.get("reportLocation"),    // human readable address
                        (String) document.get("waterType"),
                        (String) document.get("waterCondition")
                );
                reportList.add(report);
            }
        });
        return reportList;
    }

    /**
     * Deny a pending request for account registration
     * @param username identifier for denied pending user
     */
    public void deleteRequest(String username) {
        db.getCollection("registrations").deleteOne(eq("username", username));
    }

    /**
     * Delete an existing approved user
     * @param username  identifier for deleted user
     */
    public void deleteUser(String username) {

        db.getCollection("users").deleteOne(eq("username", username));

    }

    /**
     * returns the current global user for the application
     * @return the current global user
     */
    public User getGlobalUser() {

        return globalUser;

    }

    /**
     * sets the global user for the application
     * @param newUser the user that will become the new global user
     */
    public void setGlobalUser(User newUser) {

        this.globalUser = newUser;

    }

    /**
     * Push modifications to user profile to database
     * @param title         Mr., Mrs., etc.
     * @param name          real name of user
     * @param email         email address of user
     * @param homeAddress   home / mailing address of user
     */
    public void updateUser(String title, String name, String email, String homeAddress) {

        String username = globalUser.getUsername();

        db.getCollection("users").replaceOne(new Document("username", globalUser.getUsername()),
                new Document("name", name)
                        .append("username", globalUser.getUsername())
                        .append("password", globalUser.getPassword())
                        .append("accountType", globalUser.getAccountType().getAccountTypeValue())
                        .append("emailAddress", email)
                        .append("homeAddress", homeAddress)
                        .append("title", title));

        setGlobalUser(findUser(username));
    }

    /**
     * Submit a new water source report to the database
     * @param name          identifier (username) of reporter
     * @param date          timestamp for report
     * @param reportNum     unique identifier of report
     * @param location      location name
     * @param waterType
     * @param waterCondition
     */
    public void saveWaterSourceReport(String name, String date, String reportNum, String location, String waterType, String waterCondition) {

        MongoCollection<Document> sourceReports = db.getCollection("sourceReports");
        Document newReport = new Document("reporterName", name)
                .append("reportDate", date)
                .append("reportNumber", reportNum)
                .append("reportLocation", location)
                .append("waterType", waterType)
                .append("waterCondition", waterCondition);

        sourceReports.insertOne(newReport);

    }

    /**
     * fetches how many reports are already in the database and calculates next report number
     * @return the next unique report number
     */
    public int getNewReportNumber() {
        FindIterable<Document> iterable = db.getCollection("sourceReports").find();

//        ArrayList<SourceReport> reportList = new ArrayList<>();
        final int[] reportCount = {0};
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
//                SourceReport report = new SourceReport(
//                        (String) document.get("reporterName"),
//                        (String) document.get("reportDate"),
//                        (String) document.get("reportNumber"),
//                        (String) document.get("reportLocation"),
//                        (String) document.get("waterType"),
//                        (String) document.get("waterCondition")
//                );
//                reportList.add(report);
                reportCount[0] += 1;
            }
        });
      //  return reportList.size() + 1;
        return reportCount[0] + 1;
    }

}
