package com.zimmstudios.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class App 
{
    public static void main( String[] args )
    {
        MongoClientOptions options = MongoClientOptions.builder().build();
        MongoClient client = new MongoClient(new ServerAddress(), options);
        
        MongoDatabase db = client.getDatabase("test");
        
        MongoCollection<Document> coll = db.getCollection("names");
    }
}
