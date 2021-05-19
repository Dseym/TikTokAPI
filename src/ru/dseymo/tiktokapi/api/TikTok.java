package ru.dseymo.tiktokapi.api;

import ru.dseymo.tiktokapi.api.entities.User;
import ru.dseymo.tiktokapi.api.entities.Video;
import ru.dseymo.tiktokapi.api.listeners.Listener;

public interface TikTok {
	
	public void addListener(Listener listener);
	
	public void removeListener(Listener listener);
	
	public User getUser(String userUniqueId);
	
	public Video getVideo(String userUniqueId, String videoId);
	
	public void registerUserHandler(User user);
	
	public void unregisterUserHandler(User user);
	
}
