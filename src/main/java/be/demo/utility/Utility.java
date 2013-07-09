package be.demo.utility;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;

/**
 * User: massoo
 */
public class Utility {

    private static final Set<String> validRedirectionURLs;

    static {
        validRedirectionURLs = new HashSet<String>();
        validRedirectionURLs.add("https://www.google.com");
        validRedirectionURLs.add("http://www.hotmail.com");
        validRedirectionURLs.add("http://localhost:8080/demo/url/redirected");
    }

    public static String normalize(String string) {
        // decompose - recompose
        return Normalizer.normalize(string, Normalizer.Form.NFC);
    }

    static public String byteToHex(byte b) {
        // Returns hex String representation of byte b
        char hexDigit[] = {
                '0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };
        char[] array = {hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f]};
        return new String(array);
    }

    static public String charToHex(char c) {
        // Returns hex String representation of char c
        byte hi = (byte) (c >>> 8);
        byte lo = (byte) (c & 0xff);
        return byteToHex(hi) + byteToHex(lo);
    }

    static public String normalizeURLCustom(String taintedURL) throws URISyntaxException, MalformedURLException {
        URL url = new URL(taintedURL);
        URI uri = url.toURI().normalize();
        return uri.toString();
    }

    public static boolean isValidRedirectionURL(String url) {
        return validRedirectionURLs.contains(url);
    }

}
