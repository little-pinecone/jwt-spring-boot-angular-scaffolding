package in.keepgrowing.jwtspringbootangularscaffolding.dataprovider;

import in.keepgrowing.jwtspringbootangularscaffolding.cookie.Cookie;

import java.util.List;

public interface CookieJar {
    List<Cookie> getCookies(Integer amount);
}
