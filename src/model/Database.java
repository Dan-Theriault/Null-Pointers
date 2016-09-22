package model;

import com.mongodb.MongoClient;
import com.mongodb.DB;

/**
 * Created by nickhutchinson on 9/21/16.
 */
public class Database {
    public Database() {
        MongoClient mongoClient = new MongoClient( "georgiadelta.com" , 27017 );
        DB db = mongoClient.getDB( "test" );
    }
}
