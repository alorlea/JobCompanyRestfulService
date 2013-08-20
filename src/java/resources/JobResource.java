/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import com.sun.jersey.api.NotFoundException;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import model.Job;
import model.JobStore;
import model.ParamUtil;

/**
 *
 * @author Alberto Lorente Leal <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class JobResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String companyName;

    public JobResource(UriInfo uriInfo, Request request, String companyName) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.companyName = companyName;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Job getJob() {
            Job com = JobStore.getStore().get(companyName);
            if(com==null)
                    throw new NotFoundException("No such Contact.");
            return com;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putJob(JAXBElement<Job> jaxbJob) {
            Job c = jaxbJob.getValue();
            return putAndGetResponse(c);
    }

    @PUT
    public Response putJob(@Context HttpHeaders herders, byte[] in) {
            Map<String,String> params = ParamUtil.parse(new String(in));
            Job c = new Job(params.get("companyName"), params.get("description"), 
                            params.get("field"),params.get("name"));
            return putAndGetResponse(c);
    }

    private Response putAndGetResponse(Job c) {
            Response res;
            if(JobStore.getStore().containsKey(c.getName())) {
                    res = Response.noContent().build();
            } else {
                    res = Response.created(uriInfo.getAbsolutePath()).build();
            }
            JobStore.getStore().put(c.getName(), c);
            return res;
    }

    @DELETE
    public void deleteContact() {
            Job c = JobStore.getStore().remove(companyName);
            if(c==null)
                    throw new NotFoundException("No such Job.");
    }
    
}
