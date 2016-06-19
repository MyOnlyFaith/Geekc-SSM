package cn.geekc.ssm.base.webapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * Controller基类
 * 
 * @author Cool
 *
 */
public class BaseController {

	/**
	 * 创建cookie
	 *
	 * @param key
	 * @param value
	 * @param domain
	 * @param path
	 * @param expiry
	 * @param httpOnly
	 * @param secure
	 * @return
	 * @see Cookie
	 */
	public static Cookie createCookie(String key, String value, String domain, String path, int expiry,
			boolean httpOnly, boolean secure) {
		Cookie cookie = new Cookie(key, value);
		if (!StringUtils.isBlank(domain)) {
			cookie.setDomain(domain);
		}
		if (!StringUtils.isBlank(path)) {
			cookie.setPath(path);
		}
		cookie.setSecure(secure);
		cookie.setMaxAge(expiry);
		cookie.setHttpOnly(httpOnly);
		return cookie;
	}

	/**
	 * 获取web项目所在的绝对路径
	 * @param request
	 * @return
	 */
	public String getRealPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/");
	}

	/**
	 * 获取请求客户端的IP地址
	 *
	 * @param request
	 * @return
	 */
	public String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
