package in.keepgrowing.jwtspringbootangularscaffolding.cookie;

import in.keepgrowing.jwtspringbootangularscaffolding.dummydata.CustomDummy;

import java.util.List;
import java.util.stream.Collectors;

public class RandomCookieJar implements CookieJar {

    private final CustomDummy customDummy;

    public RandomCookieJar(CustomDummy customDummy) {
        this.customDummy = customDummy;
    }

    @Override
    public List<Cookie> getCookies() {
        List<String> flavours = customDummy.listOf(10, () -> customDummy.flavour().mixed());

        return flavours.stream()
                .map(Cookie::new)
                .collect(Collectors.toList());
    }
}
