package personal.learning.url.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import personal.learning.url.entity.UrlMap;

@Repository
public class UrlRepository {
	
	private final ConcurrentHashMap<Long, UrlMap> usingShortId = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, UrlMap> usingLongUrl = new ConcurrentHashMap<>();
    
    private final AtomicLong sequence = new AtomicLong(100000); // start from a large number for nice short keys
	
	public void save(UrlMap urlMap) {
	    
        usingShortId.put(urlMap.getShortUrlId(), urlMap);
        usingLongUrl.put(urlMap.getLongUrl(), urlMap);
	}

	public long getShortUrlId() {
		long id = sequence.incrementAndGet();
		return id;
	}

	public UrlMap getShortUrlByLongUrl(String longUrl) {
		return usingLongUrl.get(longUrl);
	}
	
	public UrlMap findByShortUrlId(long shortUrlId) {
		return usingShortId.get(shortUrlId);
    }

	public UrlMap deleteByShortUrlId(long shorUrlId) {
		UrlMap urlMap = usingShortId.remove(shorUrlId);
		usingLongUrl.remove(urlMap.getLongUrl());
		return urlMap;
	}

}
