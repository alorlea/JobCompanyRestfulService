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
import model.Course;
import model.ParamUtil;
import model.Transcript;
import model.TranscriptStore;

/**
 *
 * @author Alberto Lorente Leal <albll@kth.se>, <a.lorenteleal@gmail.com>
 */
public class TranscriptResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String transcriptName;

    public TranscriptResource(UriInfo uriInfo, Request request, String contact) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.transcriptName = contact;
    }
    
    @GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Transcript getTranscript() {
		Transcript cont = TranscriptStore.getStore().get(transcriptName);
		if(cont==null)
			throw new NotFoundException("No such Transcript.");
		return cont;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putContact(JAXBElement<Transcript> jaxbTranscript) {
		Transcript c = jaxbTranscript.getValue();
		return putAndGetResponse(c);
	}
	
	@PUT
	public Response putContact(@Context HttpHeaders herders, byte[] in) {
		Map<String,String> params = ParamUtil.parse(new String(in));
		Transcript c = new Transcript(params.get("name"), params.get("university"),
                                params.get("degree"), params.get("year"),
				new ArrayList<Course>());
		return putAndGetResponse(c);
	}
	
	private Response putAndGetResponse(Transcript c) {
		Response res;
		if(TranscriptStore.getStore().containsKey(c.getName())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		TranscriptStore.getStore().put(c.getName(), c);
		return res;
	}
	
	@DELETE
	public void deleteContact() {
		Transcript c = TranscriptStore.getStore().remove(transcriptName);
		if(c==null)
			throw new NotFoundException("No such Contact.");
	}
}
