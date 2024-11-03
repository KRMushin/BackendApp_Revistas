package com.mycompany.apprevistas;

import com.mycompany.apprevistas.AutenticadorFiltro.AutenticadorFiltro;
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
        packages("com.mycompany.apprevistas.restApi.resources", 
                        "com.mycompany.apprevistas.restApi.resources.Anuncios",
                        "com.mycompany.apprevistas.restApi.resources.Revistas",
                        "com.mycompany.apprevistas.restApi.resources.Suscripciones",
                        "com.mycompany.apprevistas.MapperExcepciones");
//                        register(AutenticadorFiltro.class);
                        register(MultiPartFeature.class);
    }
}    
    