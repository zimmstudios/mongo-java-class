
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

/**
 * Introduction to Freemarker.
 */
public class HelloFreemarker {
    
    public static void main(String[] args) {
        
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloFreemarker.class, "/");
        
        try {
            
            Template helloTemplate = configuration.getTemplate("hello.ftl");
            StringWriter writer = new StringWriter();
            
            Map<String, Object> helloMap = new HashMap<String, Object>();
            helloMap.put("name", "Travis");
            
            helloTemplate.process(helloMap, writer);
            
            System.out.println(writer);
            
        } catch (IOException ex) {
            
            Logger.getLogger(HelloFreemarker.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (TemplateException ex) {
            Logger.getLogger(HelloFreemarker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
