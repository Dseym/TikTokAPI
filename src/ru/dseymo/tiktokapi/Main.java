package ru.dseymo.tiktokapi;

import ru.dseymo.tiktokapi.api.TikTok;
import ru.dseymo.tiktokapi.api.builders.TikTokBuilder;

public class Main {

	public static void main(String[] args) {
		TikTok tiktok = new TikTokBuilder().build();
		System.out.println(tiktok.getVideo("dseymo", "6874520154374540546").getLikeCount());
	}

}
