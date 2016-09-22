package model;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import static java.util.Arrays.asList;

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
                if (document.get("password").equals(password)) {
                    isTrue[0] = true;
                } else {
                    isTrue[0] = false;
                }
            }
        });

        if (isTrue[0] == null) {
            return false;
        }
        return isTrue[0];
    }
}
