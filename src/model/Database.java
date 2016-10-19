package model;

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

    public void registerUser(String name, String username, String password,
            AccountType accountType) {

        MongoCollection<Document> registrations = db.getCollection("registrations");
        Document newUser = new Document("name", name)
                .append("username", username)
                .append("password", password)
                .append("accountType", accountType.getAccountTypeValue());

        registrations.insertOne(newUser);
    }

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
                        (String) document.get("reportLocation"),
                        (String) document.get("waterType"),
                        (String) document.get("waterCondition")
                );
                reportList.add(report);
            }
        });
        return reportList;
    }

    public void deleteRequest(String username) {
        db.getCollection("registrations").deleteOne(eq("username", username));
    }

    public void deleteUser(String username) {

        db.getCollection("users").deleteOne(eq("username", username));

    }

    public User getGlobalUser() {

        return globalUser;

    }

    public void setGlobalUser(User newUser) {

        this.globalUser = newUser;

    }

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

    public int getNewReportNumber() {
        FindIterable<Document> iterable = db.getCollection("sourceReports").find();

        ArrayList<SourceReport> reportList = new ArrayList<>();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                SourceReport report = new SourceReport(
                        (String) document.get("reporterName"),
                        (String) document.get("reportDate"),
                        (String) document.get("reportNumber"),
                        (String) document.get("reportLocation"),
                        (String) document.get("waterType"),
                        (String) document.get("waterCondition")
                );
                reportList.add(report);
            }
        });
        return reportList.size() + 1;
    }

}
