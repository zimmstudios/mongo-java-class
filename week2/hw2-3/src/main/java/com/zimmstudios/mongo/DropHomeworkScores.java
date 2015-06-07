package com.zimmstudios.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Projections.excludeId;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class DropHomeworkScores 
{
    public static void main( String[] args )
    {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("students");
        MongoCollection<Document> collection = db.getCollection("grades");
        
        //db.grades.find( { "type" : "homework" }, { "_id" : false } ).sort( { "student_id" : 1, "score" : 1 })
        
        Bson filter = eq("type", "homework");
        
        List<Document> all = collection
                .find(filter)
                .sort(new Document("student_id", 1).append("score", 1))
                .into(new ArrayList<Document>());
        
        for (int i = 0; i < all.size(); i++) {
            System.out.println(i + ": " + all.get(i));
        }
        System.out.println("Count: " + all.size());
        
        for (int i = 0; i < all.size(); i++) {
            // remove all even-numbered scores because they are always a student's lower score
            if (i % 2 == 0) {
                ObjectId currentId = all.get(i).getObjectId("_id");
                collection.deleteOne(eq("_id", currentId));
                System.out.println("ID deleted: " + currentId);
            }
        }
        
        // Query again after dropping lower homework scores
        List<Document> allUpdated = collection
                .find(filter)
                .sort(new Document("student_id", 1).append("score", 1))
                .into(new ArrayList<Document>());
        
        // View the updated results
        for (Document current : allUpdated) {
            System.out.println(current);
        }
        
        System.out.println("New count: " + allUpdated.size());
    }
}
