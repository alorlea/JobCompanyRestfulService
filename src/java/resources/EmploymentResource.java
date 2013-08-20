/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import com.sun.jersey.api.NotFoundException;
import java.util.ArrayList;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import model.Employment;
import model.EmploymentRecord;
import model.EmploymentStore;
import model.ParamUtil;

/**
 *
 * @author Alberto Lorente Leal <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class EmploymentResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String employmentName;

    public EmploymentResource(UriInfo uriInfo, Request request, String employmentName) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.employmentName = employmentName;
    }
    
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public EmploymentRecord getRecord() {
            EmploymentRecord cont = EmploymentStore.getStore().get(employmentName);
            if(cont==null)
                    throw new NotFoundException("No such Transcript.");
            return cont;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putEmployment(JAXBElement<EmploymentRecord> jaxbRecord) {
            EmploymentRecord c = jaxbRecord.getValue();
            return putAndGetResponse(c);
    }

    @PUT
    public Response putEmployment(@Context HttpHeaders herders, byte[] in) {
            Map<String,String> params = ParamUtil.parse(new String(in));
            EmploymentRecord c = new EmploymentRecord(params.get("name"), 
                            new ArrayList<Employment>());
            return putAndGetResponse(c);
    }

    private Response putAndGetResponse(EmploymentRecord c) {
            Response res;
            if(EmploymentStore.getStore().containsKey(c.getName())) {
                    res = Response.noContent().build();
            } else {
                    res = Response.created(uriInfo.getAbsolutePath()).build();
            }
            EmploymentStore.getStore().put(c.getName(), c);
            return res;
    }

    @DELETE
    public void deleteContact() {
            EmploymentRecord c = EmploymentStore.getStore().remove(employmentName);
            if(c==null)
                    throw new NotFoundException("No such Contact.");
    }
}
