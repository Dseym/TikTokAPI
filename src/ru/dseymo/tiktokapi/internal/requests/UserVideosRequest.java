package ru.dseymo.tiktokapi.internal.requests;

import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;

import org.jsoup.Jsoup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ru.dseymo.tiktokapi.internal.entities.DataUser;
import ru.dseymo.tiktokapi.internal.entities.DataVideo;

public class UserVideosRequest extends Request {
	
	public static int maxCount = 50;
	
	private ArrayList<JsonArray> answer = new ArrayList<>();
	
	public UserVideosRequest(DataUser user, int count, Proxy proxy) {
		super("api/item_list/?count=%count%&id=%id%&type=1&secUid=%secUid%&minCursor=0&maxCursor=0&sourceType=8&appId=1233&region=US&language=en"
				.replace("%count%", (count > maxCount ? maxCount : count) + "")
				.replace("%id%", user.id)
				.replace("%secUid%", user.secUid));
		try {
			JsonObject resp = send();
			answer.add(resp.getAsJsonArray("items"));
			while (count > maxCount && resp.get("hasMore").getAsBoolean()) {
				changeUrl("api/item_list/?count=50&id=%id%&type=1&secUid=%secUid%&minCursor=0&maxCursor=%maxCursor%&sourceType=8&appId=1233&region=US&language=en"
							.replace("%id%", user.id).replace("%secUid%", user.secUid)
							.replace("%maxCursor%", resp.get("maxCursor").getAsString()));
				resp = send();
				answer.add(resp.get("items").getAsJsonArray());
			}
		} catch (Exception e) {e.printStackTrace();}
	}
	
	@Override
	public JsonObject send() throws IOException {
		return new JsonParser().parse(Jsoup.connect(getUrl()).userAgent("Chrome").get().data()).getAsJsonObject();
	}
	
	public ArrayList<DataVideo> getVideos() {
		ArrayList<DataVideo> videos = new ArrayList<>();
		for(JsonArray arr: answer)
			for(JsonElement obj: arr)
				videos.add(DataVideo.parse(obj.getAsJsonObject()));
		
		return videos;
	}
	
}
