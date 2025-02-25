
import g.sants.challenge.api.user.User;
import g.sants.challenge.api.user.repository.UserRepository;
import g.sants.challenge.api.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
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
        List<User> users = new ArrayList<>();
       User user = new User();
       when(userRepository.save(ArgumentMatchers.any(User.class))).thenAnswer(invocation ->{
           User savedUser = invocation.getArgument(0);
           users.add(savedUser);
           return savedUser;
       });

       User createdUser = userService.createUser(user);

        assertEquals(1,users.size());

        verify(userRepository).save(user);

        assertEquals(user,createdUser);
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
        User gabriel = new User(1L, "Gabriel", "Santos", 27, "gabriel@email.com");

        userService.updateUser(1L,gabriel);


    }

    @Test
    public void deleteUserDeleteEspecifiedUser(){

    }

}