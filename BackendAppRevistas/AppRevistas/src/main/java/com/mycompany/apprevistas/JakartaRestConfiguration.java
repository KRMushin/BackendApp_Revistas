package com.mycompany.apprevistas;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;


/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("api/v1")
public class JakartaRestConfiguration extends ResourceConfig {
    
    public JakartaRestConfiguration()  {
        packages("com.mycompany.apprevistas.restApi.resources", "com.mycompany.apprevistas.MapperExcepciones").register(MultiPartFeature.class);
    }
}    
    
