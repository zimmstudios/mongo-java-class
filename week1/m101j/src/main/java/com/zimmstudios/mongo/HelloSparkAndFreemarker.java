
package com.zimmstudios.mongo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Combine Spark and Freemarker.
 */
public class HelloSparkAndFreemarker {
    
    public static void main(String[] args) {
        
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloSparkAndFreemarker.class, "/");
        
        Spark.get(new Route("/") {

            @Override
            public Object handle(Request request, Response response) {
                
                StringWriter writer = new StringWriter();
                
                try {
                    
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    
                    Map<String, Object> helloMap = new HashMap<String, Object>();
                    helloMap.put("name", "Travis");
                    
                    helloTemplate.process(helloMap, writer);
                    
                } catch (IOException ex) {
                    halt(500);
                    Logger.getLogger(HelloFreemarker.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TemplateException ex) {
                    halt(500);
                    Logger.getLogger(HelloFreemarker.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return writer;
            }
        });
    }
}
