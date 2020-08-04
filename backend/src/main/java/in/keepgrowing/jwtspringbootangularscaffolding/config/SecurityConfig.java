package in.keepgrowing.jwtspringbootangularscaffolding.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.keepgrowing.jwtspringbootangularscaffolding.security.AuthenticationFilter;
import in.keepgrowing.jwtspringbootangularscaffolding.security.AuthorizationFilter;
import in.keepgrowing.jwtspringbootangularscaffolding.security.CustomUserDetailsService;
import in.keepgrowing.jwtspringbootangularscaffolding.security.TokenProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.http.HttpServletResponse;
import java.time.Clock;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProperties tokenProperties;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomUserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;
    @Value("${cors.enabled:false}")
    private boolean corsEnabled;

    public SecurityConfig(TokenProperties tokenProperties,
                          BCryptPasswordEncoder passwordEncoder,
                          CustomUserDetailsService userDetailsService,
                          ObjectMapper objectMapper) {
        this.tokenProperties = tokenProperties;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        applyCors(httpSecurity)
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedResponse())
                .and()
                .logout()
                .and()
                .addFilter(new AuthenticationFilter(authenticationManagerBean(), tokenProperties,
                        objectMapper, Clock.systemDefaultZone()))
                .addFilterAfter(new AuthorizationFilter(tokenProperties), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, tokenProperties.getLoginPath()).permitAll()
                .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                .antMatchers("/api/users/**").hasRole("ADMIN")
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll();
    }

    private HttpSecurity applyCors(HttpSecurity httpSecurity) throws Exception {
        if (corsEnabled) {
            return httpSecurity.cors().and();
        } else {
            return httpSecurity;
        }
    }

    private AuthenticationEntryPoint unauthorizedResponse() {
        return (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
