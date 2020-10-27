package in.keepgrowing.jwtspringbootangularscaffolding.cookie;

import in.keepgrowing.jwtspringbootangularscaffolding.dummydata.CustomDummy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RandomCookieJarTest {

    private RandomCookieJar cookieJar;

    @Mock
    private CustomDummy customDummy;

    @BeforeEach
    void setUp() {
        cookieJar = new RandomCookieJar(customDummy);
    }

    @Test
    void shouldGetCookies() {
        String flavour = "coconut and chocolate";
        when(customDummy.listOf(eq(10), any()))
                .thenReturn(List.of(flavour));
        List<Cookie> expected = List.of(new Cookie(flavour));

        List<Cookie> actual = cookieJar.getCookies();

        assertEquals(expected, actual);
    }
}