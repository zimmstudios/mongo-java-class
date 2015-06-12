package com.zimmstudios.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;


public class DropHomeworkScores
{
    public static void main( String[] args )
    {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("school");
        MongoCollection<Document> collection = db.getCollection("students");
        
        List<Document> all = collection
                .find()
                .sort(new Document("_id", 1))
                .into(new ArrayList<Document>());
        
        for (Document studentDocument : all) {
            System.out.println("Student: " + studentDocument);
            
            
            List<Document> scoreList =
                    (List<Document>) studentDocument.get("scores");
            
            System.out.println("Score list: " + scoreList);
            
            double lowestHomework = 100;
            Document scoreToRemove = null;
            for (Document scoreDocument : scoreList) {
            
                if (scoreDocument.getString("type").equals("homework")) {
                    
                    if (scoreDocument.getDouble("score") < lowestHomework) {
                        
                        // update the lowest homework
                        lowestHomework = scoreDocument.getDouble("score");
                        
                        // save a reference to the score document I want to remove from the array
                        scoreToRemove = scoreDocument;
                    }
                }
            }
            
            // Remove the saved reference to the lowest homework score
            scoreList.remove(scoreToRemove);
            
            System.out.println("Score list after pruning: " + scoreList);
            
            // update this student in the database, setting the scores array to my pruned array
            collection.updateOne(
                    eq("_id", studentDocument.getDouble("_id")),
                    new Document("$set", new Document("scores", scoreList)));
            
            System.out.println("Student after updating: " + studentDocument);
        }
        
        
    }
}
