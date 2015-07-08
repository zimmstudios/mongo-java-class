package com.zimmstudios.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class App 
{
    
    public static void main( String[] args )
    {
        MongoClient c =  new MongoClient();
        MongoDatabase db = c.getDatabase("test");
        MongoCollection<Document> animals = db.getCollection("animals");
        
        Document animal = new Document("animal", "monkey");
        
        animals.insertOne(animal);          // insert 1
        animal.remove("animal");
        animal.append("animal", "cat");
        animals.insertOne(animal);          // insert 2 (fails b/c duplicate _id)
        animal.remove("animal");
        animal.append("animal", "lion");
        animals.insertOne(animal);          // insert 3 (fails b/c duplicate _id)
    }
}
