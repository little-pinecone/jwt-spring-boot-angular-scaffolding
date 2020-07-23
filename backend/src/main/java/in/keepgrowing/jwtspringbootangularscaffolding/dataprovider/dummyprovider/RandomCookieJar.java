package in.keepgrowing.jwtspringbootangularscaffolding.dataprovider.dummyprovider;

import dev.codesoapbox.dummy4j.Dummy4j;
import in.keepgrowing.jwtspringbootangularscaffolding.cookie.Cookie;
import in.keepgrowing.jwtspringbootangularscaffolding.dataprovider.CookieJar;

import java.util.ArrayList;
import java.util.List;

public class RandomCookieJar implements CookieJar {

    private final Dummy4j dummy4j;
    private final FlavourDefinitions flavourDefinitions;

    public RandomCookieJar(Dummy4j dummy4j) {
        this.dummy4j = dummy4j;
        this.flavourDefinitions = new FlavourDefinitions(this.dummy4j);
    }

    public List<Cookie> getCookies(Integer amount) {
        List<String> flavours = generateUniqueFlavours(amount);
        List<Cookie> cookies = new ArrayList<>();
        for (String flavour : flavours) {
            var cookie = new Cookie(flavour);
            cookies.add(cookie);
        }
        return cookies;
    }

    private List<String> generateUniqueFlavours(Integer amount) {
        return dummy4j.unique().value("uniqueFlavours", () -> flavourDefinitions.mixed(amount));
    }
}
