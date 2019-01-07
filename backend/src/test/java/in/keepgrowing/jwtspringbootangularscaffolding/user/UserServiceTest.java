package in.keepgrowing.jwtspringbootangularscaffolding.user;

import in.keepgrowing.jwtspringbootangularscaffolding.user.model.User;
import in.keepgrowing.jwtspringbootangularscaffolding.user.model.UserCredentials;
import in.keepgrowing.jwtspringbootangularscaffolding.user.model.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder encoder;
    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserService(userRepository, encoder);
    }

    @Test
    public void register() {
        User user = createTestUser();
        when(userRepository.save(user))
                .thenReturn(user);
        userService.register(user);

        verify(userRepository, times(1)).save(user);
    }

    private User createTestUser() {
        UserCredentials userCredentials = new UserCredentials("user", "password", "ROLE_USER");
        return new User(userCredentials);
    }
}