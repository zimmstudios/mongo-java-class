package com.zimmstudios.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bson.Document;
import org.bson.conversions.Bson;

public class FindWithProjectionTest {
    
    public static void main(String[] args) {
        
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithProjectionTest");
        
        collection.drop();
        
        // insert 10 documents with two random integers
        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document()
                .append("x", new Random().nextInt(2))
                .append("y", new Random().nextInt(100))
                .append("i", i)
            );
        }
        
        Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));
        
        Bson exclude = new Document("x", false).append("_id", false);
        Bson include = new Document("y", true).append("i", true);
        
        Bson projection = fields(include("y", "i"), excludeId());
                
        List<Document> all = collection
                .find(filter)
                .projection(projection)
                .into(new ArrayList<Document>());
        
        for (Document current : all) {
            System.out.println(current);
        }
        
        long count = collection.count(filter);
        System.out.println("Count: " + count);
    }
    
}
