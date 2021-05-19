package ru.dseymo.tiktokapi.internal.entities;

import java.util.ArrayList;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lombok.Getter;
import ru.dseymo.tiktokapi.api.builders.TikTokBuilder;
import ru.dseymo.tiktokapi.api.entities.Tag;
import ru.dseymo.tiktokapi.api.entities.User;
import ru.dseymo.tiktokapi.api.entities.Video;
import ru.dseymo.tiktokapi.internal.requests.VideoRequest;

public class DataVideo implements Video {
	public static DataVideo parse(JsonObject item) {
		JsonObject jsonVideo = item.getAsJsonObject("video");
		JsonObject statsVideo = item.getAsJsonObject("stats");
		
		DataVideo video = new DataVideo();
		video.id = item.get("id").getAsString();
		video.description = item.get("desc").getAsString();
		video.duration = jsonVideo.get("duration").getAsLong();
		video.likeCount = statsVideo.get("diggCount").getAsLong();
		video.commentCount = statsVideo.get("commentCount").getAsLong();
		video.playCount = statsVideo.get("playCount").getAsLong();
		video.shareCount = statsVideo.get("shareCount").getAsLong();
		video.userUniqueId = item.getAsJsonObject("author").get("uniqueId").getAsString();
		
		if(item.get("challenges") != null)
			for(JsonElement jsonTag: item.get("challenges").getAsJsonArray()) {
				JsonObject json = jsonTag.getAsJsonObject();
				DataTag tag = new DataTag();
				tag.id = json.get("id").getAsString();
				tag.title = json.get("title").getAsString();
				video.tags.add(tag);
			}
		
		return video;
	}
	
	
	@Getter
	public String id, description;
	@Getter
	public long duration, playCount, shareCount, commentCount, likeCount;
	public ArrayList<DataTag> tags = new ArrayList<>();
	@Getter
	public String userUniqueId;
	
	public ArrayList<Tag> getTags() {return new ArrayList<>(tags);}
	
	public boolean update() {
		if(id == null || id.isEmpty() || userUniqueId == null || userUniqueId.isEmpty()) return false;
		
		VideoRequest req = new VideoRequest(userUniqueId, id);
		DataVideo video = req.getVideo();
		if(video == null) return false;
		
		id = video.id;
		description = video.description;
		duration = video.duration;
		likeCount = video.likeCount;
		commentCount = video.commentCount;
		playCount = video.playCount;
		shareCount = video.shareCount;
		userUniqueId = video.userUniqueId;
		tags = video.tags;
		
		return true;
	}
	
	public User getAuthor() {
		return new TikTokBuilder().build().getUser(userUniqueId);
	}
	
	
	@Override
	public boolean equals(Object _video) {
		if(_video == null || !(_video instanceof DataVideo)) return false;
		DataVideo video = (DataVideo)_video;
		
		return video.id.equals(id);
	}
	
}
