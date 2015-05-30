
package com.zimmstudios.mongo;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Introduction to Spark.
 */
public class HelloSpark {
    
    public static void main(String[] args) {
        
        Spark.get(new Route("/") {

            @Override
            public Object handle(Request request, Response response) {
                return "Hello world from Spark!";
            }
            
        });
        
    }
}
