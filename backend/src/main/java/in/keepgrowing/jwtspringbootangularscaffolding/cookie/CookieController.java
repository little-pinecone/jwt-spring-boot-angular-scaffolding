package in.keepgrowing.jwtspringbootangularscaffolding.cookie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cookies")
public class CookieController {

    private final CookieJar cookieJar;

    public CookieController(CookieJar cookieJar) {
        this.cookieJar = cookieJar;
    }

    @GetMapping
    public List<Cookie> getCookies() {
        return cookieJar.getCookies();
    }
}
