package in.keepgrowing.jwtspringbootangularscaffolding.config;

import dev.codesoapbox.dummy4j.Dummy4jBuilder;
import in.keepgrowing.jwtspringbootangularscaffolding.dataprovider.CookieJar;
import in.keepgrowing.jwtspringbootangularscaffolding.dataprovider.dummyprovider.RandomCookieJar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class BeanConfiguration {

    @Bean
    public CookieJar randomCookieJar() {
        var dummy4j = new Dummy4jBuilder()
                .locale(Collections.singletonList("fi"))
                .seed(1L)
                .build();
        return new RandomCookieJar(dummy4j);
    }
}
