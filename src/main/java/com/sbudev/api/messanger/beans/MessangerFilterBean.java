package com.sbudev.api.messanger.beans;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

public class MessangerFilterBean {
	private @QueryParam("year") int year;
	private @QueryParam("start") int start;
	private @QueryParam("size")int size;
	private @PathParam("commentId") long commentId;
	private @PathParam("messageId") long messageId;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public long getMessageId() {
		return messageId;
	}
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	
	
	
}
