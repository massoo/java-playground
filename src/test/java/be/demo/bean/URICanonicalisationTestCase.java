package be.demo.bean;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.owasp.esapi.ESAPI;

import static org.hamcrest.CoreMatchers.*;

public class URICanonicalisationTestCase {
	
	private String decodedUrl = "http://www.someurl.com/has spaces in url";
	private String mixedUrl = "http://www.someurl.com/has%20spaces%20in%20url";
	private String encodedUrl = "http://www.someurl.com%2Fhas%20spaces%20in%20url";
	
	@Test
	public void testGoodURICanonicalization() {
		String canonicalMixedUrl = ESAPI.encoder().canonicalize(mixedUrl);
		String canonicalEncodedUrl = ESAPI.encoder().canonicalize(encodedUrl);
		
		Assert.assertThat(decodedUrl, equalTo(canonicalEncodedUrl));
		Assert.assertThat(decodedUrl, equalTo(canonicalMixedUrl));
	}
	
	@Test
	public void testBadURICanonicalization() throws URISyntaxException, MalformedURLException {
		URI mixedURL = new URI(mixedUrl);
		URI encodedURL = new URI(encodedUrl);
		
		Assert.assertThat(decodedUrl, not(equalTo(mixedURL.toURL().toString())));
		Assert.assertThat(decodedUrl, not(equalTo(encodedURL.toURL().toString())));
	}
	
}
