package com.zimmstudios.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class FindTest {
    
    public static void main(String[] args) {
        
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findTest");
        
        collection.drop();
        
        // insert 10 documents
        for (int i = 0; i < 10; i++) {
            collection.insertOne(new Document("x", i));
        }
        
        System.out.println("Find one:");
        Document first = collection.find().first();
        System.out.println(first);
        
        System.out.println("Find all with into:");
        List<Document> all = collection.find().into(new ArrayList<Document>());
        for (Document current : all) {
            System.out.println(current);
        }
        
        System.out.println("Find all with iteration:");
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
            
        } finally {
            // Make sure you always close the cursor
            cursor.close();
        }
        
        System.out.println("Count:");
        long count = collection.count();
        System.out.println(count);
    }
    
}
