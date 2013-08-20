/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import com.sun.jersey.api.NotFoundException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.GenericType;
//import com.sun.jersey.api.client.WebResource;
import javax.xml.bind.JAXBElement;
import model.*;
import sun.misc.Regexp;

/**
 * Entry point to access the web service to access the employment portal
 * @author Alberto Lorente Leal <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class ApplicationResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String nameApplicant;
    String companyName;
    String urlJob="http://localhost:9999/RestHW4/resources/Jobs";
    String urlEmployment="http://localhost:9999/RestHW4/resources/employments";
    String urlCompany="http://localhost:9999/RestHW4/resources/companies";
    String urlTranscript="http://localhost:9999/RestHW4/resources/transcripts";
    

    public ApplicationResource(UriInfo uriInfo, Request request, String params) {
        this.uriInfo = uriInfo;
        this.request = request;
        String [] data = params.split(",");
        this.nameApplicant= data[0];
        this.companyName=data[1];
//        client = Client.create();
    }
  
}
