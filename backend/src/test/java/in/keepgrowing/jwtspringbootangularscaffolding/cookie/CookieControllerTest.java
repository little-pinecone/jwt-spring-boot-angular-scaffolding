package in.keepgrowing.jwtspringbootangularscaffolding.cookie;

import dev.codesoapbox.dummy4j.Dummy4j;
import in.keepgrowing.jwtspringbootangularscaffolding.config.SecurityConfig;
import in.keepgrowing.jwtspringbootangularscaffolding.dataprovider.CookieJar;
import in.keepgrowing.jwtspringbootangularscaffolding.dataprovider.dummyprovider.RandomCookieJar;
import in.keepgrowing.jwtspringbootangularscaffolding.security.CustomUserDetailsService;
import in.keepgrowing.jwtspringbootangularscaffolding.security.TokenProperties;
import in.keepgrowing.jwtspringbootangularscaffolding.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CookieController.class)
@Import({TokenProperties.class, BCryptPasswordEncoder.class, CustomUserDetailsService.class, SecurityConfig.class,
        RandomCookieJar.class, Dummy4j.class})
public class CookieControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Autowired
    private CookieJar cookieJar;

    @Test
    @WithMockUser(roles = "USER")
    public void getsCookies() throws Exception {

        mvc.perform(get("/api/cookies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0]", is(notNullValue())))
                .andExpect(status().isOk());
    }

}