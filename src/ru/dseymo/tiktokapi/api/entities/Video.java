package ru.dseymo.tiktokapi.api.entities;

import java.util.ArrayList;

public interface Video {
	
	public String getId();
	
	public String getDescription();
	
	public long getDuration();
	
	public long getPlayCount();
	
	public long getShareCount();
	
	public long getCommentCount();
	
	public long getLikeCount();
	
	public ArrayList<Tag> getTags();
	
	public String getUserUniqueId();
	
	public User getAuthor();
	
	public boolean update();
	
}
