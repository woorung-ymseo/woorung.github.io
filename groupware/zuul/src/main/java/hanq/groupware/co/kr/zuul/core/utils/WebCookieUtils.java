package hanq.groupware.co.kr.zuul.core.utils;

import hanq.groupware.co.kr.zuul.core.context.ResponseContextHolder;
import lombok.Builder;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Setter
public class WebCookieUtils {

    /**
     * 쿠키 추가
     *
     * @param cookie
     */
    public static void addCookie(Cookie cookie) {
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();

        HttpServletResponse response = attr.getResponse();

        response.addCookie(cookie);
    }

    /**
     * 쿠키 추가
     *
     * @param key
     * @param value
     */
    public static void addCookie(String key, String value) {
        addCookie(key, value, -1);
    }

    /**
     * 쿠키 추가
     *
     * @param key
     * @param value
     * @param maxAge
     */
    public static void addCookie(String key, String value, int maxAge) {
        addCookie(key, value, maxAge, "");
    }

    /**
     * 쿠키 추가
     *
     * @param key
     * @param value
     * @param maxAge
     * @param domain
     */
    public static void addCookie(String key, String value, int maxAge, String domain) {
        Cookie cookie = new Cookie(key, value);

        cookie.setMaxAge(maxAge);
        cookie.setDomain(domain);

        addCookie(cookie);
    }
    /**
     * 쿠키 얻기
     */
    public static Cookie getCookie(String name) {
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();

        HttpServletRequest request = attr.getRequest();

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {

            if (StringUtils.equals(name, cookie.getName())) {
                return cookie;
            }
        }

        return null;
    }

    /**
     * 쿠키 값 얻기
     *
     * @param name
     * @return
     */
    public static String getCookieValue(String name) {
        Cookie cookie = getCookie(name);

        String value = cookie != null ? cookie.getValue() : "";

        return value;
    }

    /**
     * 만료 값 얻기
     *
     * @param name
     * @return
     */
    public static int getCookieMaxAge(String name) {
        Cookie cookie = getCookie(name);

        int maxAge = cookie != null ? cookie.getMaxAge() : 0;

        return maxAge;
    }

    /**
     * 도메인 값 얻기
     *
     * @param name
     * @return
     */
    public static String getCookieDomain(String name) {
        Cookie cookie = getCookie(name);

        String domain = cookie != null ? cookie.getDomain() : "";

        return domain;
    }

    /**
     * 쿠키 값 수정
     *
     * @param name
     * @param value
     * @return
     */
    public static void modifyCookieValue(String name, String value) {
        Cookie cookie = getCookie(name);

        cookie.setValue(value);

        addCookie(cookie);
    }

    /**
     * 쿠키 만료일 수정
     *
     * @param name
     * @param maxAge
     * @return
     */
    public static void modifyCookieMaxAge(String name, int maxAge) {
        Cookie cookie = getCookie(name);

        cookie.setMaxAge(maxAge);

        addCookie(cookie);
    }

    /**
     * 쿠키 도메인 수정
     *
     * @param name
     * @param domain
     * @return
     */
    public static void modifyCookieDomain(String name, String domain) {
        Cookie cookie = getCookie(name);

        cookie.setDomain(domain);

        addCookie(cookie);
    }

    /**
     * 쿠키 제거
     *
     * @param key
     */
    public static void removeCookie(String key) {
        Cookie cookie = new Cookie(key, null);

        cookie.setMaxAge(0);
        cookie.setDomain(null);

        addCookie(cookie);
    }
}
