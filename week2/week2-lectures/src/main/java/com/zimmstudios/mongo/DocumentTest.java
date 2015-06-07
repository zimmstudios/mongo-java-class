package com.zimmstudios.mongo;

import java.util.Arrays;
import java.util.Date;
import org.bson.Document;
import org.bson.types.ObjectId;

public class DocumentTest {
    
    public static void main(String[] args) {
        
        Document document = new Document()
                .append("str", "Hello MongoDB")
                .append("int", 42)
                .append("long", 1L)
                .append("double", 1.1)
                .append("boolean", true)
                .append("date", new Date())
                .append("objectId", new ObjectId())
                .append("null", null)
                .append("embeddedDoc", new Document("x", 0))
                .append("list", Arrays.asList(1, 2, 3, 4))
                ;
        
        System.out.println(document);
    }
}
