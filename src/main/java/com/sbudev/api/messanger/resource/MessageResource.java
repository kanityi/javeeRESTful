package com.sbudev.api.messanger.resource;




import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


import com.sbudev.api.messanger.beans.MessangerFilterBean;
import com.sbudev.api.messanger.model.Message;
import com.sbudev.api.messanger.service.MessageService;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	
	@GET
	public List<Message> getMessages(@BeanParam MessangerFilterBean filterBean){
		if(filterBean.getYear() > 0)
			return messageService.getAllMessagesForAyear(filterBean.getYear());
		if(filterBean.getStart() > 0 && filterBean.getSize() > 0)
			return messageService.getAllMessagePaginated(filterBean.getStart(), filterBean.getSize());
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
		Message mssg = messageService.getMessage(id);
		//String uri = uriInfo.getAbsolutePath().toString();
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(Long.toString(mssg.getId()))
				.build()
				.toString();
		
		mssg.addLink(uri, "self");
		
		return mssg;
	}
	
	@POST
	public Response addMessage(@Context UriInfo uriInfo, Message message){
		
		Message mssg = messageService.addMessage(message);
		String mssgId = String.valueOf(mssg.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(mssgId).build();
		
		return Response.created(uri)
				.entity(mssg)
				.build();
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") int id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public Message removeMessge(@PathParam("messageId") long id) {
		return messageService.removeMessage(id);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource commentResource() {
		return new CommentResource();
	}
	
	
}
