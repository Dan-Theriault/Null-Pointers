package model;

import com.mongodb.MongoClient;

/**
 * Created by nickhutchinson on 9/21/16.
 */
public class Database {
    public Database() {
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
    }
}
