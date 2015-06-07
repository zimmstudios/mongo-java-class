package com.zimmstudios.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.gte;
import com.mongodb.client.model.UpdateOptions;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class DeleteTest {
    
    public static void main(String[] args) {
        
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("deleteTest");
        
        collection.drop();
        
        // insert 8 documents, with _id set to the loop variable
        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document().append("_id", i));
        }
        
        collection.deleteMany(gt("_id", 4));
        collection.deleteOne(eq("_id", 2));
        
        List<Document> all = collection.find().into(new ArrayList<Document>());
        
        for (Document current : all) {
            System.out.println(current);
        }
        
    }
    
}
