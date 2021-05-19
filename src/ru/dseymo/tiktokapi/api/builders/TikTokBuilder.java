package ru.dseymo.tiktokapi.api.builders;

import ru.dseymo.tiktokapi.api.TikTok;
import ru.dseymo.tiktokapi.internal.DataTiktok;

public class TikTokBuilder {
	
	public TikTok build() {
		
		DataTiktok tiktok = DataTiktok.getInstance();
		
		return tiktok;
	}
	
}
