package in.keepgrowing.jwtspringbootangularscaffolding.cookie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/cookies")
public class CookieController {

    @GetMapping
    public List<Cookie> getCookies() {
        return Arrays.asList(new Cookie("chocolate"), new Cookie("vanilla"),
                new Cookie("cinnamon"), new Cookie("coconut"));
    }
}
