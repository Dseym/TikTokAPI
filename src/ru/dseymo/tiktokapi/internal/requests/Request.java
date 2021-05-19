package ru.dseymo.tiktokapi.internal.requests;

import java.io.IOException;
import java.util.Random;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.Getter;

public class Request {
	@Getter
	private String url;
	
	public Request(String url) {
		changeUrl(url);
	}
	
	public void changeUrl(String url) {
		this.url = "https://www.tiktok.com/" + url;
	}
	
	public JsonObject send() throws IOException {
		Connection c = Jsoup.connect(url);
		c.userAgent("Opera");
		c.cookie("tiktok", new Random().nextInt(Integer.MAX_VALUE) + "");
		c.cookie("useragent", "operabrowser");
		return new JsonParser().parse(c.get().getElementById("__NEXT_DATA__").data()).getAsJsonObject();
	}
	
}
