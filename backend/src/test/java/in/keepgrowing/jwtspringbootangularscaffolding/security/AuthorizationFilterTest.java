package in.keepgrowing.jwtspringbootangularscaffolding.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorizationFilterTest {

    private static final String HEADER_NAME = "Authorisation";
    private static final String HEADER_PREFIX = "Bearer ";

    @Mock
    private TokenProperties tokenProperties;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private Claims claims;

    @Mock
    private JwtParser jwtParser;

    private AuthorizationFilter filter;

    @BeforeEach
    void setUp() {
        filter = new AuthorizationFilter(tokenProperties, jwtParser);
        when(tokenProperties.getHeader())
                .thenReturn(HEADER_NAME);
    }

    @Test
    void shouldSkipGettingClaimsWhenHeaderIsNull() throws ServletException, IOException {
        when(request.getHeader(HEADER_NAME))
                .thenReturn(null);
        filter.doFilterInternal(request, response, filterChain);
        verify(claims, times(0)).getSubject();
    }

    @Test
    void shouldSkipGettingClaimsWhenInvalidHeader() throws ServletException, IOException {
        when(request.getHeader(HEADER_NAME))
                .thenReturn("faulty");
        when(tokenProperties.getPrefix())
                .thenReturn(HEADER_PREFIX);
        filter.doFilterInternal(request, response, filterChain);
        verify(claims, times(0)).getSubject();
    }
}