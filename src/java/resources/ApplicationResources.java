/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

/**
 * Access point of the web service to access the profile of the user
 * @author Alberto Lorente Leal <albll@kth.se>, <a.lorenteleal@gmail.com>
 */

@Path("/Application")
public class ApplicationResources {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    @Path("{params}")
    public ApplicationResource getProfile(
                    @PathParam("params") String params) {
            return new ApplicationResource(uriInfo, request, params);
    }
}
