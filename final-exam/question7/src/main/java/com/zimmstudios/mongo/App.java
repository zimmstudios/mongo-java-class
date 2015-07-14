package com.zimmstudios.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("final7");
        MongoCollection<Document> albumsCollection = db.getCollection("albums");
        MongoCollection<Document> imagesCollection = db.getCollection("images");
        
        List<Document> allImages = imagesCollection
                .find()
                .into(new ArrayList<Document>());
        List<Document> allAlbums = albumsCollection
                .find()
                .into(new ArrayList<Document>());
        
        int numDeleted = 0;
        
        for (Document image : allImages) {
            
            boolean orphan = true;
            
            double id = image.getDouble("_id");
            
            for (Document album : allAlbums) {
                
                List<Double> imageList =
                    (List<Double>) album.get("images");
                
                if (imageList.contains(id)) {
                    orphan = false;
                }
                
            }
            
            if (orphan) {
                imagesCollection.deleteOne(eq("_id", id));
                System.out.println("Deleted image with _id: " + id);
                numDeleted++;
            }
        }
        
        System.out.println("Num deleted: " + numDeleted);
    }
}
