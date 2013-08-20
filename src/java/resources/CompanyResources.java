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
import model.Company;
import model.CompanyStore;

/**
 *
 * @author Alberto
 */

@Path("/companies")
public class CompanyResources {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Company> getCompanies(){
        List<Company> companies = new ArrayList<Company>();
        companies.addAll(CompanyStore.getStore().values());
        return companies;
    }
    
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
            int count = CompanyStore.getStore().size();
            return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newContact(
                    @FormParam("name") String name,
                    @FormParam("location") String location,
                    @FormParam("genre") String genre,
                    @Context HttpServletResponse servletResponse
    ) throws IOException {
            Company c = new Company();
            CompanyStore.getStore().put(name, c);

            URI uri = uriInfo.getAbsolutePathBuilder().path(name).build();
            Response.created(uri).build();

            servletResponse.sendRedirect("../pages/new_company.html");
    }

    @Path("{companyName}")
    public CompanyResource getContact(
                    @PathParam("companyName") String companyName) {
            return new CompanyResource(uriInfo, request, companyName);
    }
            
}
