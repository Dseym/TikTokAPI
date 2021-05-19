package ru.dseymo.tiktokapi.internal.requests;

import com.google.gson.JsonObject;

import ru.dseymo.tiktokapi.internal.entities.DataVideo;

public class VideoRequest extends Request {
	
	private JsonObject answer;
	
	public VideoRequest(String userUniqueId, String videoId) {
		super("@%uniqueId%/video/%videoId%"
				.replace("%uniqueId%", userUniqueId)
				.replace("%videoId%", videoId));
		
		try {
			answer = send().getAsJsonObject("props").getAsJsonObject("pageProps").getAsJsonObject("itemInfo").getAsJsonObject("itemStruct");
		} catch (Exception e) {e.printStackTrace();};
	}
	
	public DataVideo getVideo() {
		return DataVideo.parse(answer);
	}

}
