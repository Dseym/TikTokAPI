package ru.dseymo.tiktokapi.api.entities;

import java.util.ArrayList;

public interface User {
	
	public String getNickname();
	
	public String getId();
	
	public String getSecUid();
	
	public String getDescription();
	
	public String getUniqueId();
	
	public ArrayList<Video> getLastVideos();
	
	public ArrayList<Video> getAllVideos(boolean cached);
	
	public long getFollowerCount();
	
	public long getVideoCount();
	
	public long getFollowingCount();
	
	public boolean update();
	
}
