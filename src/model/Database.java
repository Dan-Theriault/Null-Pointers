package model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import com.mongodb.client.result.DeleteResult;
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
        return isTrue[0];
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

    public ArrayList<User> getUsers() {
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

    public void deleteRequest(String username) {
        db.getCollection("registrations").deleteOne(eq("username", username));
    }

}
