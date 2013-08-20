/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import model.Job;
import model.JobStore;

/**
 *
 * @author Alberto
 */
@Path("/Jobs")
public class JobsResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Job> getRecords() {
            List<Job> contacts = new ArrayList<Job>();
            contacts.addAll(JobStore.getStore().values() );
            return contacts;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
            int count = JobStore.getStore().size();
            return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newContact(
                    @FormParam("companyName") String companyName,
                    @FormParam("description") String description,
                    @FormParam("field") String field,
                    @FormParam("name") String name,
                    
                    
                    @Context HttpServletResponse servletResponse
    ) throws IOException {
            Job c = new Job(companyName, description, field, name);
            JobStore.getStore().put(companyName, c);

            URI uri = uriInfo.getAbsolutePathBuilder().path(name).build();
            Response.created(uri).build();

            servletResponse.sendRedirect("../pages/new_Job.html");
    }

    @Path("{companyName}")
    public JobResource getJob(
                    @PathParam("companyName") String companyName) {
            return new JobResource(uriInfo, request, companyName);
    }
    
}
