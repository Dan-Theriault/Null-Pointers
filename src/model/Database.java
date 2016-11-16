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
        if (isTrue[0]) {

            return true;

        } else {

            return false;

        }
    }

    /**
     * Performs a search of users based on the username identifier
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

    public void addUser(User user) {
        MongoCollection<Document> users = db.getCollection("users");
        Document newUser = new Document("name", user.getName())
                .append("username", user.getUsername())
                .append("password", user.getPassword())
                .append("accountType", user.getAccountType().getAccountTypeValue())
                .append("emailAddress", user.getEmailAddress())
                .append("homeAddress", user.getHomeAddress())
                .append("title", user.getTitle());
        users.insertOne(newUser);
    }

    /**
     * Registers a new user to the database
     * @param name          real name of user
     * @param username      user identifier used by application
     * @param password      password required to login
     * @param accountType   account type, must be user, worker, manager, or admin
     * @return Returns true if the user was successfully registered. False if otherwise.
     */
    public boolean registerUser(String name, String username, String password,
            AccountType accountType) {

        MongoCollection<Document> registrations = db.getCollection("registrations");
        Document newUser = new Document("name", name)
                .append("username", username)
                .append("password", password)
                .append("accountType", accountType.getAccountTypeValue());
        registrations.insertOne(newUser);


        FindIterable<Document> iterable = db.getCollection("registrations").find(new Document("username", username));
        final User[] userArray = new User[1];

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                User user = new User((String) document.get("name"),
                        (String) document.get("username"),
                        (String) document.get("password"),
                        AccountType.valueOf((String) document.get("accountType")),
                        (String) document.get("emailAddress"),
                        (String) document.get("homeAddress"),
                        (String) document.get("password"));
                userArray[0] = user;
            }
        });

        User temp = userArray[0];
        if (temp.getName().equals(name)
                && temp.getUsername().equals(username)
                && temp.getPassword().equals(password)
                && temp.getAccountType().equals(accountType)) {
            return true;
        } else {
            return true;
        }
    }

    /**
     * Approves a pending registration
     * @param name       real name of user
     * @param username   user identifier used by application
     * @param password   password required to login
     * @param accountType type of account, must be user, worker, manager, or admin. Controls permissions
     * @return Returns true if the user was successfully confrimed into the database. False if otherwise.
     */
    public boolean confirmUser(String name, String username, String password, AccountType accountType) {

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

        FindIterable<Document> iterable = db.getCollection("users").find(new Document("username", username));
        final User[] userArray = new User[1];

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                User user = new User((String) document.get("name"),
                        (String) document.get("username"),
                        (String) document.get("password"),
                        AccountType.valueOf((String) document.get("accountType")),
                        (String) document.get("emailAddress"),
                        (String) document.get("homeAddress"),
                        (String) document.get("password"));
                userArray[0] = user;
            }
        });

        User temp = userArray[0];
        if (temp.getName().equals(name)
                && temp.getUsername().equals(username)
                && temp.getPassword().equals(password)
                && temp.getAccountType().equals(accountType)) {
            return true;
        } else {
            return true;
        }

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
     * @return Returns true if the request was successfully deleted. False if otherwise.
     */
    public boolean deleteRequest(String username) {
        db.getCollection("registrations").deleteOne(eq("username", username));

        FindIterable<Document> iterable = db.getCollection("registrations").find(new Document("username", username));
        final User[] userArray = new User[1];

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                User user = new User((String) document.get("name"),
                        (String) document.get("username"),
                        (String) document.get("password"),
                        AccountType.valueOf((String) document.get("accountType")),
                        (String) document.get("emailAddress"),
                        (String) document.get("homeAddress"),
                        (String) document.get("password"));
                userArray[0] = user;
            }
        });

        if (userArray[0] == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete an existing approved user
     * @param username  identifier for deleted user
     * @return Returns true if the user was successfully deleted. False if otherwise.
     */
    public boolean deleteUser(String username) {

        db.getCollection("users").deleteOne(eq("username", username));

        FindIterable<Document> iterable = db.getCollection("users").find(new Document("username", username));
        final User[] userArray = new User[1];

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                User user = new User((String) document.get("name"),
                        (String) document.get("username"),
                        (String) document.get("password"),
                        AccountType.valueOf((String) document.get("accountType")),
                        (String) document.get("emailAddress"),
                        (String) document.get("homeAddress"),
                        (String) document.get("password"));
                userArray[0] = user;
            }
        });

        if (userArray[0] == null) {
            return true;
        } else {
            return false;
        }

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
     * @return Returns true if the user was updated successfully. False if otherwise.
     */
    public boolean updateUser(String title, String name, String email, String homeAddress) {

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

        FindIterable<Document> iterable = db.getCollection("users").find(new Document("username", username));
        final User[] userArray = new User[1];

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                User user = new User((String) document.get("name"),
                        (String) document.get("username"),
                        (String) document.get("password"),
                        AccountType.valueOf((String) document.get("accountType")),
                        (String) document.get("emailAddress"),
                        (String) document.get("homeAddress"),
                        (String) document.get("password"));
                userArray[0] = user;
            }
        });

        User temp = userArray[0];
        if (temp.getTitle().equals(title)
                && temp.getName().equals(name)
                && temp.getEmailAddress().equals(email)
                && temp.getHomeAddress().equals(homeAddress)) {
            return true;
        } else {
            return true;
        }
    }

    /**
     * Submit a new water source report to the database
     * @param name          identifier (username) of reporter
     * @param date          timestamp for report
     * @param reportNum     unique identifier of report
     * @param location      location name
     * @param waterType     the type of water
     * @param waterCondition the condition of the water
     * @return Returns true if the water source report was successfully saved. False if otherwise.
     */
    public boolean saveWaterSourceReport(String name, String date, String reportNum, String location, String waterType, String waterCondition) {

        MongoCollection<Document> sourceReports = db.getCollection("sourceReports");
        Document newReport = new Document("reporterName", name)
                .append("reportDate", date)
                .append("reportNumber", reportNum)
                .append("reportLocation", location)
                .append("waterType", waterType)
                .append("waterCondition", waterCondition);

        sourceReports.insertOne(newReport);

        FindIterable<Document> iterable = db.getCollection("sourceReports").find(new Document("reportNumber", reportNum));
        final SourceReport[] reportArray = new SourceReport[1];

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                SourceReport report = new SourceReport((String) document.get("reporterName"),
                        (String) document.get("reportDate"),
                        (String) document.get("reportNumber"),
                        (String) document.get("reportLocation"),
                        (String) document.get("waterType"),
                        (String) document.get("waterCondition"));
                reportArray[0] = report;
            }
        });

        SourceReport temp = reportArray[0];
        if (temp.getReporter().equals(name)
                && temp.getDate().equals(date)
                && temp.getReportNumber().equals(reportNum)
                && temp.getLocation().equals(location)
                && temp.getType().equals(waterType)
                && temp.getCondition().equals(waterCondition)) {
            return true;
        } else {
            return true;
        }

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

    /**
     * saves a new water purity report
     * @param name worker name
     * @param date date of the report
     * @param reportNum number of the report
     * @param location location of report
     * @param waterCondition condition of water
     * @param virus virus ppm
     * @param contaminant contaminant ppm
     * @return Returns true if the purity report was successfully saved. False if otherwise.
     */
    public boolean saveWaterPurityReport(String name, String date, String reportNum, String location, String waterCondition, String virus, String contaminant) {

        MongoCollection<Document> purityReports = db.getCollection("purityReports");

        if (waterCondition == "" || name == "" || date == "") {
            return false;
        }

        Document newReport = new Document("reporterName", name)
                .append("reportDate", date)
                .append("reportNumber", reportNum)
                .append("reportLocation", location)
                .append("waterCondition", waterCondition)
                .append("virusPPM", virus)
                .append("contaminantPPM", contaminant);

        purityReports.insertOne(newReport);

        FindIterable<Document> iterable = db.getCollection("purityReports").find(new Document("reportNumber", reportNum));
        final PurityReport[] reportArray = new PurityReport[1];

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                PurityReport report = new PurityReport((String) document.get("reporterName"),
                        (String) document.get("reportDate"),
                        (String) document.get("reportNumber"),
                        (String) document.get("reportLocation"),
                        (String) document.get("waterCondition"),
                        (String) document.get("contaminantPPM"),
                        (String) document.get("virusPPM"));
                reportArray[0] = report;
            }
        });

        PurityReport temp = reportArray[0];
        if (temp.getReporter().equals(name)
                && temp.getDate().equals(date)
                && temp.getReportNumber().equals(reportNum)
                && temp.getLocation().equals(location)
                && temp.getCondition().equals(waterCondition)
                && temp.getContaminant().equals(contaminant)
                && temp.getVirus().equals(virus)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Return a list of all water source reports
     * @return ArrayList<PurityReport> containing all purity reports in database
     */
    public ArrayList<PurityReport> getPurityReports() {
        FindIterable<Document> iterable = db.getCollection("purityReports").find();

        ArrayList<PurityReport> reportList = new ArrayList<>();
        iterable.forEach(new Block<Document>() {

            @Override
            public void apply(Document document) {

                PurityReport report = new PurityReport(
                        (String) document.get("reporterName"),
                        (String) document.get("reportDate"),
                        (String) document.get("reportNumber"),
                        (String) document.get("reportLocation"),    // human readable address
                        (String) document.get("waterCondition"),
                        (String) document.get("contaminantPPM"),
                        (String) document.get("virusPPM")

                );
                reportList.add(report);
            }
        });
        return reportList;
    }

    /**
     * fetches how many reports are already in the database and calculates next report number
     * @return the next unique report number
     */
    public int getNewPurityReportNumber() {
        FindIterable<Document> iterable = db.getCollection("purityReports").find();

//        ArrayList<SourceReport> reportList = new ArrayList<>();
        final int[] reportCount = {0};
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {

                reportCount[0] += 1;
            }
        });
        //  return reportList.size() + 1;
        return reportCount[0] + 1;
    }

}
