/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import model.Company;
import model.CompanyStore;
import com.sun.jersey.api.NotFoundException;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import model.ParamUtil;

/**
 * Web service to access and modify the company information profile.
 * 
 * @author Alberto Lorente Leal <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class CompanyResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String companyName;

    public CompanyResource(UriInfo uriInfo, Request request, String companyName) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.companyName = companyName;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Company getCompany() {
            Company com = CompanyStore.getStore().get(companyName);
            if(com==null)
                    throw new NotFoundException("No such Contact.");
            return com;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putContact(JAXBElement<Company> jaxbCompany) {
            Company c = jaxbCompany.getValue();
            return putAndGetResponse(c);
    }

    @PUT
    public Response putContact(@Context HttpHeaders herders, byte[] in) {
            Map<String,String> params = ParamUtil.parse(new String(in));
            Company c = new Company(params.get("name"), params.get("location"), 
                            params.get("genre"));
            return putAndGetResponse(c);
    }

    private Response putAndGetResponse(Company c) {
            Response res;
            if(CompanyStore.getStore().containsKey(c.getName())) {
                    res = Response.noContent().build();
            } else {
                    res = Response.created(uriInfo.getAbsolutePath()).build();
            }
            CompanyStore.getStore().put(c.getName(), c);
            return res;
    }

    @DELETE
    public void deleteContact() {
            Company c = CompanyStore.getStore().remove(companyName);
            if(c==null)
                    throw new NotFoundException("No such Company.");
    }
}
