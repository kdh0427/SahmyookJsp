package util;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class Cookies {

	private Map<String, Cookie> cookieMap = new HashMap<>();

	public Cookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
	}

	public Cookie getCookie(String name) {
		return cookieMap.get(name);
	}

	public String getValue(String name) throws IOException {
		Cookie cookie = cookieMap.get(name);
		if (cookie == null) {
			return null;
		}
		return URLDecoder.decode(cookie.getValue(), "UTF-8");
	}

	public boolean exists(String name) {
		return cookieMap.containsKey(name);
	}

	public static Cookie createCookie(String name, String value) throws IOException {
		return new Cookie(name, URLEncoder.encode(value, "UTF-8"));
	}

	public static Cookie createCookie(String name, String value, String path, int maxAge) throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}

	public static Cookie createCookie(String name, String value, String path, int maxAge, String domain)
			throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		cookie.setDomain(domain);
		return cookie;
	}
}