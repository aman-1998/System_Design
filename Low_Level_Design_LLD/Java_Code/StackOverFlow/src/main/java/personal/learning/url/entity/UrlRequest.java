package personal.learning.url.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UrlRequest {
	
	@JsonProperty("longUrl")
	private String longUrl;
	
	public UrlRequest() {}
	
	public UrlRequest(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	@Override
	public String toString() {
		return "UrlRequest [longUrl=" + longUrl + "]";
	}

}
