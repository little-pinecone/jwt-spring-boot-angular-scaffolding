package in.keepgrowing.jwtspringbootangularscaffolding.cookie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CookieController.class)
public class CookieControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles = "USER")
    public void getsCookies() throws Exception {
        mvc.perform(get("/api/cookies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].flavour", is("chocolate")))
                .andExpect(jsonPath("$.[1].flavour", is("vanilla")))
                .andExpect(status().isOk());
    }

}