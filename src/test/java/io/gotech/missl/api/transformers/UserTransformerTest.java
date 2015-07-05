package io.gotech.missl.api.transformers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import io.gotech.missl.api.responses.UserInfo;
import io.gotech.missl.domain.elections.VoteWeight;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserAuthSource;
import io.gotech.missl.domain.users.UserDTO;
import io.gotech.missl.domain.users.UserGender;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.domain.users.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTransformerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    private UserTransformer transformer;
    private final Long USER_INFO_ID = new Long(4);
    private final UserInfo USER_INFO = new UserInfo(USER_INFO_ID, "John",
	    "Doe", 3);
    private final UserDTO USER_DTO = new UserDTO(new UserId(USER_INFO_ID),
	    UserGender.MALE, new VoteWeight(3), new UserAuthSource(
		    UserAuthSource.Source.FACEBOOK, "1112ldj3"), "John", "Doe");

    @Before
    public void initialise() {
	transformer = new UserTransformer(userRepository);
	Mockito.when(user.getDTO()).thenReturn(USER_DTO);
    }

    @Test
    public void transformFromUserInfoRetrieveTheUserFromTheUserInfoID()
	    throws Exception {
	transformer.transformFrom(USER_INFO);
	Mockito.verify(userRepository, Mockito.times(1)).findById(
		Mockito.any(UserId.class));
    }

    @Test
    public void transformToUserInfoTransformAUserObjectToAnExpectedUserInfoObject() {
	UserInfo userInfo = transformer.transformTo(user);
	verifyExpectedUserInfoReturned(userInfo);
    }

    private void verifyExpectedUserInfoReturned(UserInfo userInfo) {
	assertEquals(USER_DTO.firstName, userInfo.firstName);
	assertEquals(USER_DTO.lastName, userInfo.lastName);
	assertTrue(USER_DTO.voteWeight.weight == userInfo.voteWeight);
	assertEquals(USER_DTO.id.id, userInfo.id);

    }

}
