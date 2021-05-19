package ru.dseymo.tiktokapi.internal.handlers;

import java.util.Timer;
import java.util.TimerTask;

import ru.dseymo.tiktokapi.internal.DataTiktok;

public abstract class Handler extends TimerTask {
	
	private Timer timer;
	private boolean registered = false;
	protected boolean isValid = true;;
	
	protected DataTiktok tiktok;
	
	public Handler(DataTiktok tiktok) {
		if(tiktok == null) {
			isValid = false;
			return;
		}
		this.tiktok = tiktok;
	}
	
	protected void register(long period) {
		if(!isValid || registered) return;
		
		timer = new Timer();
		timer.schedule(this, period, period);
		
		registered = true;
	}
	
	public void unregister() {
		if(!registered || timer == null) return;
		
		timer.cancel();
		
		registered = false;
	}

}
