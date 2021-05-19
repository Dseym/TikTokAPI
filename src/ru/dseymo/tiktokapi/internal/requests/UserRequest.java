package ru.dseymo.tiktokapi.internal.requests;

import java.util.ArrayList;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ru.dseymo.tiktokapi.internal.entities.DataUser;
import ru.dseymo.tiktokapi.internal.entities.DataVideo;

public class UserRequest extends Request {
	
	private JsonObject answer;
	
	public UserRequest(String userUniqueId) {
		super("@%uniqueId%?lang=en".replace("%uniqueId%", userUniqueId));
		try {
			answer = send().getAsJsonObject("props").getAsJsonObject("pageProps");
		} catch (Exception e) {e.printStackTrace();};
	}
	
	public DataUser getUser() {
		return DataUser.parse(answer.getAsJsonObject("userInfo"));
	}
	
	public ArrayList<DataVideo> getLastVideos() {
		if(answer == null) return null;
		
		ArrayList<DataVideo> videos = new ArrayList<>();
		for(JsonElement obj: answer.getAsJsonArray("items"))
			videos.add(DataVideo.parse(obj.getAsJsonObject()));
		
		return videos;
	}
	
}
