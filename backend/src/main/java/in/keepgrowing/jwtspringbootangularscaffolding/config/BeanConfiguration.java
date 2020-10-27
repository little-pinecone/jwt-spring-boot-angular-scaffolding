package in.keepgrowing.jwtspringbootangularscaffolding.config;

import in.keepgrowing.jwtspringbootangularscaffolding.cookie.CookieJar;
import in.keepgrowing.jwtspringbootangularscaffolding.cookie.RandomCookieJar;
import in.keepgrowing.jwtspringbootangularscaffolding.dummydata.CustomDummy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CookieJar cookieJar() {
        return new RandomCookieJar(new CustomDummy());
    }
}
