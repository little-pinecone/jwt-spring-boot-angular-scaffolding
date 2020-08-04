package in.keepgrowing.jwtspringbootangularscaffolding.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.keepgrowing.jwtspringbootangularscaffolding.user.model.UserCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.FilterChain;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationFilterTest {

    private final static LocalDate LOCAL_DATE = LocalDate.of(2000, Month.JANUARY, 1);
    private final Clock clock = Clock.fixed(LOCAL_DATE.atStartOfDay(ZoneId.systemDefault()).toInstant(),
            ZoneId.systemDefault());

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenProperties tokenProperties;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ServletInputStream servletInputStream;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private Authentication authentication;

    @Mock
    private FilterChain filterChain;

    @Mock
    private GrantedAuthority grantedAuthority;

    private AuthenticationFilter filter;

    @BeforeEach
    void setUp() {
        when(tokenProperties.getLoginPath())
                .thenReturn("login/");
        filter = new AuthenticationFilter(authenticationManager, tokenProperties, objectMapper, clock);
    }

    @Test
    void shouldAuthenticate() throws IOException {
        var credentials = new UserCredentials("user", "test", "role");
        when(request.getInputStream())
                .thenReturn(servletInputStream);
        when(objectMapper.readValue(servletInputStream, UserCredentials.class))
                .thenReturn(credentials);
        var token = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword(),
                emptyList()
        );
        when(authenticationManager.authenticate(token))
                .thenReturn(authentication);

        assertNotNull(filter.attemptAuthentication(request, response));
    }

    @Test
    void shouldThrowExceptionWhenCantReadCredentials() throws IOException {
        when(request.getInputStream())
                .thenReturn(servletInputStream);
        when(objectMapper.readValue(servletInputStream, UserCredentials.class))
                .thenThrow(IOException.class);

        assertThrows(UnreadableCredentialsException.class, () -> filter.attemptAuthentication(request, response));
    }

    @Test
    void shouldAddHeaderForAuthenticatedUser() {
        when(tokenProperties.getHeader())
                .thenReturn("header");
        when(tokenProperties.getPrefix())
                .thenReturn("prefix");
        when(tokenProperties.getExpiration())
                .thenReturn(1);
        when(tokenProperties.getSecret())
                .thenReturn("secret");
        when(authentication.getName())
                .thenReturn("name");
        List<GrantedAuthority> grantedAuthorities = singletonList(grantedAuthority);
        doReturn(grantedAuthorities)
                .when(authentication).getAuthorities();
        when(grantedAuthority.getAuthority())
                .thenReturn("test");

        filter.successfulAuthentication(request, response, filterChain, authentication);
        var val = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYW1lIiwiYXV0aG9yaXRpZXMiOlsidGVzdCJdLCJpYXQiOjk0NjY4MTIwMCwiZXhwIj" +
                "o5NDY2ODEyMDF9.S8WGUAHCGMbvFcC4FsYjJ-7L98hJO4uWqp5oHeajNSHkKSFHU6IgrIO3ZNWnH6XMTfIzeoUeyO8pWwcOkXs9ZA";

        verify(response).addHeader("header", "prefix" + val);
    }
}