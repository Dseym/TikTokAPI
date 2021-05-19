package ru.dseymo.tiktokapi.internal.entities;

import java.util.ArrayList;

import com.google.gson.JsonObject;

import lombok.Getter;
import ru.dseymo.tiktokapi.api.entities.User;
import ru.dseymo.tiktokapi.api.entities.Video;
import ru.dseymo.tiktokapi.internal.requests.UserRequest;
import ru.dseymo.tiktokapi.internal.requests.UserVideosRequest;

public class DataUser implements User {
	public static DataUser parse(JsonObject info) {
		JsonObject jsonUser = info.get("user").getAsJsonObject();
		JsonObject stats = info.get("stats").getAsJsonObject();
		
		DataUser user = new DataUser();
		user.videoCount = stats.get("videoCount").getAsLong();
		user.followerCount = stats.get("followerCount").getAsLong();
		user.followingCount = stats.get("followingCount").getAsLong();
		user.nickname = jsonUser.get("nickname").getAsString();
		user.uniqueId = jsonUser.get("uniqueId").getAsString();
		user.id = jsonUser.get("id").getAsString();
		user.description = jsonUser.get("signature").getAsString();
		user.secUid = jsonUser.get("secUid").getAsString();
		
		return user;
	}
	
	
	@Getter
	public String uniqueId, nickname, id, description, secUid;
	@Getter
	public long followerCount, videoCount, followingCount;
	public ArrayList<DataVideo> lastVideos = new ArrayList<>();
	public ArrayList<DataVideo> cachedVideos = new ArrayList<>();
	
	public ArrayList<Video> getLastVideos() {return new ArrayList<>(lastVideos);}
	
	public ArrayList<Video> getAllVideos(boolean cached) {
		if(cached && cachedVideos.size() != 0) return new ArrayList<>(cachedVideos);
		
		cachedVideos = new UserVideosRequest(this, (int)videoCount, null).getVideos();
		
		return new ArrayList<>(cachedVideos);
	}
	
	public boolean update() {
		if(uniqueId == null || uniqueId.isEmpty()) return false;
		
		UserRequest req = new UserRequest(uniqueId);
		DataUser user = req.getUser();
		if(user == null) return false;
		
		videoCount = user.videoCount;
		followerCount = user.followerCount;
		followingCount = user.followingCount;
		nickname = user.nickname;
		uniqueId = user.uniqueId;
		id = user.id;
		description = user.description;
		secUid = user.secUid;
		lastVideos = req.getLastVideos();
		
		return true;
	}
	
	
	@Override
	public boolean equals(Object _user) {
		if(_user == null || !(_user instanceof DataUser)) return false;
		
		return ((DataUser)_user).uniqueId.equals(uniqueId);
	}
	
}
