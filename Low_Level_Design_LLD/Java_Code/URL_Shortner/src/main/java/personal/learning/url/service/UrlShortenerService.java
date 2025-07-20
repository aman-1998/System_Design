package personal.learning.url.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import personal.learning.url.entity.UrlMap;
import personal.learning.url.repository.UrlRepository;

@Service
public class UrlShortenerService {
	
	 private static final String DOMAIN = "http://localhost:9191/tinyurl/";
	 
	 @Autowired
	 private UrlRepository urlRepository;
	 
	 public UrlMap shortenUrl(String longUrl) {
		 UrlMap urlMap = urlRepository.getShortUrlByLongUrl(longUrl);
		 if(urlMap == null) {
			 long shortUrlId = urlRepository.getShortUrlId();
			 String shortKey = Base62Encoder.encode(shortUrlId);
	         String shortUrl = DOMAIN + shortKey;
	         urlMap = new UrlMap(shortUrlId, longUrl);
	         urlMap.setShortUrl(shortUrl);
	        
	         LocalDateTime now = LocalDateTime.now();
	         LocalDateTime future = now.plusYears(10);
	         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	         String futureExpiryDate = future.format(formatter);
	        
	         urlMap.setExpiryDate(futureExpiryDate);
	        
	         String currentDateTimeStr = now.format(formatter);
	         urlMap.setCreatedAt(currentDateTimeStr);
	        
	         urlRepository.save(urlMap);
		 }   
	     return urlMap;
     }
	 
	 public UrlMap expand(String shortKey) {
         long shorUrlId = Base62Encoder.decode(shortKey);
         UrlMap urlMap = urlRepository.findByShortUrlId(shorUrlId);
         return urlMap;
     }

	public boolean deleteByShortKey(String shortKey) {
		long shorUrlId = Base62Encoder.decode(shortKey);
		UrlMap urlMap = urlRepository.deleteByShortUrlId(shorUrlId);
		return urlMap != null ? true : false;
	}
}
