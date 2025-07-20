package personal.learning.url.entity;

public class UrlMap {
	
	private long shortUrlId;
	
	private String longUrl;
	
	private String shortUrl;
	
	private String createdAt;
	
	private String expiryDate;

	public UrlMap(long shortUrlId, String longUrl) {
		this.shortUrlId = shortUrlId;
		this.longUrl = longUrl;
	}

	public long getShortUrlId() {
		return shortUrlId;
	}

	public void setShortUrlId(long shortUrlId) {
		this.shortUrlId = shortUrlId;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "UrlMap [shortUrlId=" + shortUrlId + ", longUrl=" + longUrl + ", shortUrl=" + shortUrl + ", createdAt=" + createdAt
				+ ", expiryDate=" + expiryDate + "]";
	}
	
}
