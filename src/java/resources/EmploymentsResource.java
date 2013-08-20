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
import model.Employment;
import model.EmploymentRecord;
import model.EmploymentStore;

/**
 *
 * @author Alberto Lorente Leal <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
@Path("/employments")
public class EmploymentsResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<EmploymentRecord> getRecords() {
            List<EmploymentRecord> contacts = new ArrayList<EmploymentRecord>();
            contacts.addAll(EmploymentStore.getStore().values() );
            return contacts;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
            int count = EmploymentStore.getStore().size();
            return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newContact(
                    @FormParam("name") String name,
                    
                    @Context HttpServletResponse servletResponse
    ) throws IOException {
            EmploymentRecord c = new EmploymentRecord(name, new ArrayList<Employment>());
            EmploymentStore.getStore().put(name, c);

            URI uri = uriInfo.getAbsolutePathBuilder().path(name).build();
            Response.created(uri).build();

            servletResponse.sendRedirect("../pages/new_employmentRecord.html");
    }

    @Path("{name}")
    public EmploymentResource getEmploymentRecord(
                    @PathParam("name") String name) {
            return new EmploymentResource(uriInfo, request, name);
    }

}
