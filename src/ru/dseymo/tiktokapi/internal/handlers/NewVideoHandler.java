package ru.dseymo.tiktokapi.internal.handlers;

import java.util.ArrayList;

import lombok.Getter;
import ru.dseymo.tiktokapi.api.listeners.Listener;
import ru.dseymo.tiktokapi.internal.DataTiktok;
import ru.dseymo.tiktokapi.internal.entities.DataUser;
import ru.dseymo.tiktokapi.internal.entities.DataVideo;
import ru.dseymo.tiktokapi.internal.requests.UserVideosRequest;

public class NewVideoHandler extends Handler {
	
	private DataVideo lastVideo;
	@Getter
	private DataUser user;
	
	public NewVideoHandler(DataTiktok tiktok, DataUser user) {
		super(tiktok);
		if(user == null) {
			isValid = false;
			return;
		}
		
		this.user = user;
		lastVideo = user.lastVideos.size() == 0 ? null : user.lastVideos.get(0);
		
		register(10000);
	}
	
	public void checkNewVideo() {
		if(!isValid) return;
		
		ArrayList<DataVideo> videos = new UserVideosRequest(user, 1, null).getVideos();
		DataVideo video = videos.size() == 0 ? null : videos.get(0);
		if(video == null || video.equals(lastVideo)) return;
		
		lastVideo = video;
		for(Listener l: tiktok.getListeners())
			l.onNewVideo(tiktok, video, user);
	}

	
	@Override
	public void run() {checkNewVideo();}
	
}
