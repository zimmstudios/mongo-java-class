package com.zimmstudios.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.*;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

public class FindWithSortSkipLimitTest {
    
    public static void main(String[] args) {
        
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithProjectionTest");
        
        collection.drop();
        
        // insert 100 documents with two integers
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                collection.insertOne(new Document().append("i", i).append("j", j));
            }
        }
        
        Bson projection = fields(include("i", "j"), excludeId());
        
        Bson sortDoc = new Document("i", 1).append("j", -1);
        Bson sortBuilder = orderBy(ascending("i"), descending("j"));
        
        List<Document> all = collection
                .find()
                .projection(projection)
                .sort(sortBuilder)
                .skip(20)
                .limit(50)
                .into(new ArrayList<Document>());
        
        for (Document current : all) {
            System.out.println(current);
        }
    }
    
}
