package com.sbudev.api.messanger.resource;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sbudev.api.messanger.beans.MessangerFilterBean;
import com.sbudev.api.messanger.model.Comment;
import com.sbudev.api.messanger.model.ErrorMessage;
import com.sbudev.api.messanger.service.CommentService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class CommentResource {
	
	CommentService commentService = new CommentService();
	
	@GET
	public List<Comment> getComments(@BeanParam MessangerFilterBean filterBean) {
		return commentService.getComments(filterBean.getMessageId());
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@BeanParam MessangerFilterBean filterBean) {
		
		Comment comment = commentService.getComment(filterBean.getMessageId(), filterBean.getCommentId());
		
		ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "Not Documented Yet.");
		
		 Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
		
		return comment;
	}
	
	@POST
	public Comment addComment(@BeanParam MessangerFilterBean filterBean, Comment comment) {
		return commentService.addComment(filterBean.getMessageId(), comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@BeanParam MessangerFilterBean filterBean, Comment comment) {
		
		return commentService.updateComment(filterBean.getMessageId(), comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public Comment removeComment(@BeanParam MessangerFilterBean filterBean) {
		return commentService.removeComment(filterBean.getMessageId(), filterBean.getCommentId());
	}
}
