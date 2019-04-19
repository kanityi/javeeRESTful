package com.sbudev.api.messanger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.sbudev.api.messanger.database.Database;
import com.sbudev.api.messanger.exception.DataNotFoundException;
import com.sbudev.api.messanger.model.Message;

public class MessageService {
	
	private Map<Long, Message> messages = Database.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1L, "Hello world!", "Sbu"));
		messages.put(2L, new Message(2L, "Hello Jersy!", "Sbu"));
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	
	public List<Message> getAllMessagesForAyear(int year) {
		
		List<Message> messagesForYear = new ArrayList<Message>();
		
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year)
				messagesForYear.add(message);
		}
		
		return messagesForYear;
	}
	
	public List<Message> getAllMessagePaginated(int start, int size){
		List<Message> list = new ArrayList<Message>(messages.values());
		if(start + size > list.size()) return new ArrayList<Message>();
		return list.subList(start, start + size);
	}
	
	public Message getMessage(long id){
		Message mssg = messages.get(id);
		if(mssg == null)
			throw new DataNotFoundException("Message with id "+id +" not found ");
		
		return mssg;
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message){
		
		if(message.getId() <= 0)
			return null;
		
		messages.put(message.getId(), message);
		
		return message;
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);
	}

}
