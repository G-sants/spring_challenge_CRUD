
import g.sants.challenge.api.user.User;
import g.sants.challenge.api.user.repository.UserRepository;
import g.sants.challenge.api.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ChallengeApplicationTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUserCreatesAnUser(){
        User user = null;
       userService.createUser(user);

        List<User> users = Arrays.asList(user);
        assertEquals(1,users.size());
    }

    @Test
    public void getAllReturnAllUsersAndNames() {
        User gabriel = new User(1L, "Gabriel", "Santos", 27, "gabriel@email.com");
        User camilla = new User(2L, "Camilla", "Farias", 12, "camilla@email.com");
        User pedro = new User(3L, "Pedro", "Santos", 16, "pedro@email.com");

        List<User> mockUsers = Arrays.asList(gabriel, camilla, pedro);
        when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> users = userService.getAllUsers();

        assertEquals(3, users.size());
        assertEquals("Gabriel", users.get(0).getName());
        assertEquals("Camilla", users.get(1).getName());
        assertEquals("Pedro", users.get(2).getName());
    }

    @Test
    public void getUserByIDReturnEspecifiedUser(){
        Long id = 1L;
        User gabriel = new User(1L, "Gabriel", "Santos", 27, "gabriel@email.com");

        when(userRepository.findById(id)).thenReturn(Optional.of(gabriel));
        User user = userService.getUser(id);

        assertEquals("Gabriel",user.getName());
    }

    @Test
    public void putUpdateEspecifiedUser(){


    }

    @Test
    public void deleteUserDeleteEspecifiedUser(){

    }

}