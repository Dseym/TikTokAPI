package ru.dseymo.tiktokapi.internal;

import java.util.ArrayList;

import lombok.Getter;
import ru.dseymo.tiktokapi.api.TikTok;
import ru.dseymo.tiktokapi.api.entities.User;
import ru.dseymo.tiktokapi.api.entities.Video;
import ru.dseymo.tiktokapi.api.listeners.Listener;
import ru.dseymo.tiktokapi.internal.entities.DataUser;
import ru.dseymo.tiktokapi.internal.entities.DataVideo;
import ru.dseymo.tiktokapi.internal.handlers.Handler;
import ru.dseymo.tiktokapi.internal.handlers.NewVideoHandler;

public class DataTiktok implements TikTok {
	
	public static DataTiktok getInstance() {
		return new DataTiktok();
	}
	
	
	@Getter
	private ArrayList<Listener> listeners = new ArrayList<>();
	@Getter
	private ArrayList<Handler> handlers = new ArrayList<>();
	
	public void addListener(Listener l) {
		if(listeners.contains(l)) return;
		
		listeners.add(l);
	}
	
	public void removeListener(Listener l) {
		if(!listeners.contains(l)) return;
		
		listeners.remove(l);
	}
	
	public void registerUserHandler(User user) {handlers.add(new NewVideoHandler(this, (DataUser)user));}
	
	public void unregisterUserHandler(User user) {
		for(Handler handler: handlers)
			if(handler instanceof NewVideoHandler)
				if(user.equals(((NewVideoHandler)handler).getUser()))
					unregisterHandler(handler);
	}
	
	public void unregisterHandler(Handler handler) {
		if(!handlers.contains(handler)) return;
		
		handler.unregister();
		handlers.remove(handler);
	}
	
	public User getUser(String uniqueId) {
		DataUser user = new DataUser();
		user.uniqueId = uniqueId;
		return user.update() ? user : null;
	}

	public Video getVideo(String userUniqueId, String videoId) {
		DataVideo video = new DataVideo();
		video.userUniqueId = userUniqueId;
		video.id = videoId;
		return video.update() ? video : null;
	}
	
}
