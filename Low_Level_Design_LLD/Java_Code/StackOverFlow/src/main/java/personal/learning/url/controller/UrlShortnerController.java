package personal.learning.url.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import personal.learning.genric.exception.UrlNotFoundException;
import personal.learning.url.entity.UrlMap;
import personal.learning.url.entity.UrlRequest;
import personal.learning.url.service.UrlShortenerService;

// Video Link : https://www.youtube.com/watch?v=AVztRY77xxA
// Video Link: https://www.youtube.com/watch?v=9csfoQK2T8g

@RestController
@RequestMapping("/tinyurl")
public class UrlShortnerController {
	
	@Autowired
	private UrlShortenerService urlShortenerService;
	
	@PostMapping("/shorten")
	public ResponseEntity<?> shotenUrl(@RequestBody UrlRequest urlRequest) {
		UrlMap urlMap = urlShortenerService.shortenUrl(urlRequest.getLongUrl());
		return ResponseEntity.status(HttpStatus.OK).body(urlMap.getShortUrl());
	}
	
	@GetMapping("/{shortKey}")
	public RedirectView redirectToOriginal(@PathVariable("shortKey") String shortKey) throws UrlNotFoundException {
		UrlMap urlMap = urlShortenerService.expand(shortKey);
		if(urlMap == null) {
			throw new UrlNotFoundException("URL not found for code: " + shortKey) ;
		}
		return new RedirectView(urlMap.getLongUrl());
	}
	
	@DeleteMapping("/{shortKey}")
	public ResponseEntity<String> deleteByShortKey(@PathVariable("shortKey") String shortKey) {
		boolean deleted = urlShortenerService.deleteByShortKey(shortKey);
		if(!deleted) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Short URL not found for shortkey " + shortKey);
		}
		return ResponseEntity.status(HttpStatus.OK).body("Short URL with shortkey " + shortKey + " is deleted");
	}
}
