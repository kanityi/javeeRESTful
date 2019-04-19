package com.sbudev.api.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbudev.api.messanger.database.Database;
import com.sbudev.api.messanger.model.Profile;

public class ProfileService {
private Map<String, Profile> profiles = Database.getProfiles();
	
	public ProfileService() {
		profiles.put("Sbuda", new Profile(1L, "Sbuda", "Sbusi", "Kani"));
		profiles.put("Siweh", new Profile(2L, "Siweh!", "Sanel", "MAdesi"));
	}
	
	public List<Profile> getAllProfiless(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		
		if(profile.getProfileName().isEmpty())
			return null;
		
		profiles.put(profile.getProfileName(), profile);
		
		return profile;
	}
	
	public Profile removeProfile(String profileName){
		return profiles.remove(profileName);
	}
}
