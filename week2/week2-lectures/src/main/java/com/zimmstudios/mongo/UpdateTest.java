package com.zimmstudios.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import com.mongodb.client.model.UpdateOptions;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class UpdateTest {
    
    public static void main(String[] args) {
        
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("updateTest");
        
        collection.drop();
        
        // insert 8 documents, with both _id and x set to the loop variable
        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document().append("_id", i).append("x", i));
        }
        
        collection.replaceOne(eq("x", 5), new Document("_id", 5).append("x", 20).append("updated", true));
        collection.updateOne(eq("x", 6), new Document("$set", new Document("x", 20)));
        collection.updateOne(eq("_id", 9), new Document("$set", new Document("x", 20)), new UpdateOptions().upsert(true));
        collection.updateMany(gte("_id", 5), new Document("$inc", new Document("x", 1)));
        
        List<Document> all = collection.find().into(new ArrayList<Document>());
        
        for (Document current : all) {
            System.out.println(current);
        }
    }
}
