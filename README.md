# TikTokAPI 1.0
Read-Only API for TikTok

## Install to your project
pom.xml
```xml
<repositories>
  <repository>
  	<id>Dseymo-Repo</id>
  	<url>http://f0461095.xsph.ru</url>
  </repository>
</repositories>
  
<dependencies>
  <dependency>
	<groupId>ru.dseymo.tiktokapi</groupId>
	<artifactId>TiktokAPI</artifactId>
	<version>1.0</version>
  </dependency>
</dependencies>
```

## API
Example
```java
TikTok tiktok = new TikTokBuilder().build();
tiktok.getVideo("dseymo", "6878957168029125889");
User user = tiktok.getUser("dseymo");

tiktok.registerUserHandler(user); //user handler (for Listener)
//New Listener
tiktok.addListener(new Listener() {
			
	public void onNewVideo(TikTok tiktok, Video video, User user) {
		System.out.println("New video");
	}
	
});
```

For free usage.
