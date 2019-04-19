package com.sbudev.api.messanger.database;

import java.util.HashMap;
import java.util.Map;

import com.sbudev.api.messanger.model.*;

	public class Database {
	
	private static Map<Long, Message> messages = new HashMap<>(); 
	private static Map<String, Profile> profiles = new HashMap<>(); 
	
	public static Map<Long, Message> getMessages(){
		return messages;
	}
	
	
	public static Map<String, Profile> getProfiles(){
		return profiles;
	}
}
