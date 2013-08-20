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
import model.Course;
import model.Transcript;
import model.TranscriptStore;

/**
 *
 * @author Alberto Lorente Leal <albll@kth.se>, <a.lorenteleal@gmail.com>
 */

@Path("/transcripts")
public class TranscriptsResource {
    @Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Transcript> getTranscripts() {
		List<Transcript> contacts = new ArrayList<Transcript>();
		contacts.addAll(TranscriptStore.getStore().values() );
		return contacts;
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = TranscriptStore.getStore().size();
		return String.valueOf(count);
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newContact(
			@FormParam("name") String name,
			@FormParam("univerity") String university,
                        @FormParam("degree") String degree,
                        @FormParam("year") String year,
                        
			@Context HttpServletResponse servletResponse
	) throws IOException {
		Transcript c = new Transcript(name, university, degree, year, new ArrayList<Course>());
		TranscriptStore.getStore().put(name, c);
		
		URI uri = uriInfo.getAbsolutePathBuilder().path(name).build();
		Response.created(uri).build();
		
		servletResponse.sendRedirect("../pages/new_transcript.html");
	}
	
	@Path("{name}")
	public TranscriptResource getTranscript(
			@PathParam("name") String name) {
		return new TranscriptResource(uriInfo, request, name);
	}
}
