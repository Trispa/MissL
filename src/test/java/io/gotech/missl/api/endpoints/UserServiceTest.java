package io.gotech.missl.api.endpoints;

import static org.junit.Assert.assertTrue;
import io.gotech.missl.api.requests.CreateUserRequest;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserAuthSource;
import io.gotech.missl.domain.users.UserBuilder;
import io.gotech.missl.domain.users.UserGender;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.domain.users.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    public final UserId USERID = new UserId(new Long(4));
    private final UserGender GENDER = UserGender.FEMALE;
    private final String FIRST_NAME = "John";
    private final String LAST_NAME = "Doe";
    private final UserAuthSource AUTH_SOURCE = new UserAuthSource(
	    UserAuthSource.Source.FACEBOOK, "1l5f5");

    @Mock
    private UserRepository userRepository;

    private UserBuilder userBuilder;
    private UserService userService;
    private User user;

    @Before
    public void initialise() {
	Mockito.doAnswer(new Answer<Void>() {

	    @Override
	    public Void answer(InvocationOnMock invocation) throws Throwable {
		User user = (User) invocation.getArguments()[0];
		user.assignId(USERID);
		return null;
	    }

	}).when(userRepository).addUser(Mockito.any(User.class));

	userBuilder = new UserBuilder();
	userService = new UserService(userBuilder, userRepository);
    }

    @Test
    public void givenACreateUserRequestwhenCreateUserItShouldCreateTheUser() {
	CreateUserRequest request = new CreateUserRequest(FIRST_NAME,
		LAST_NAME, AUTH_SOURCE, GENDER);
	user = userService.create(request);
	assertTrue(user.getId().equals(USERID));
    }
}
