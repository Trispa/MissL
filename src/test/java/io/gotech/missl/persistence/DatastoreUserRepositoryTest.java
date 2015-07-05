package io.gotech.missl.persistence;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static org.junit.Assert.assertEquals;
import io.gotech.missl.domain.elections.VoteWeight;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserAuthSource;
import io.gotech.missl.domain.users.UserDTO;
import io.gotech.missl.domain.users.UserGender;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.persistence.entities.UserEntity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DatastoreUserRepositoryTest extends PersistenceTest {

    private final UserId USER_ID = UserId.NOT_SET;
    private final VoteWeight USER_VOTE_WEIGHT = new VoteWeight(1);
    private final UserGender GENDER = UserGender.FEMALE;
    private final String FIRST_NAME = "John";
    private final String LAST_NAME = "Doe";
    private final UserAuthSource AUTH_SOURCE = new UserAuthSource(
	    UserAuthSource.Source.FACEBOOK, "1l5f5");

    @Mock
    private UserEntityDTOTransformer transformer;
    @Mock
    private User user;
    @Mock
    private UserDTO userDTO;

    private DatastoreUserRepository repository;

    @Before
    public void initialise() {
	Mockito.when(user.getDTO()).thenReturn(userDTO);
	UserEntity userEntity = new UserEntity(USER_ID.id,
		AUTH_SOURCE.authSource.name(), AUTH_SOURCE.authID,
		GENDER.name(), USER_VOTE_WEIGHT.weight, FIRST_NAME, LAST_NAME);
	Mockito.when(transformer.toEntity(userDTO)).thenReturn(userEntity);

	repository = new DatastoreUserRepository(transformer);

    }

    @Test
    public void givenAUserWhenSaveUserItSavesTheUserToTheDataStore() {
	repository.saveUser(user);
	verifyThatUserIsSavedToDatastore();
    }

    private void verifyThatUserIsSavedToDatastore() {
	Mockito.verify(user).getDTO();
	Mockito.verify(transformer).toEntity(userDTO);
	Mockito.verify(user).assignId(Mockito.any(UserId.class));
	UserEntity entity = ofy().load().type(UserEntity.class)
		.filter("authSource", AUTH_SOURCE.authSource.name())
		.filter("authId", AUTH_SOURCE.authID).first().now();
	assertEquals(FIRST_NAME, entity.firstName);
	assertEquals(LAST_NAME, entity.lastName);
	assertEquals(USER_VOTE_WEIGHT.weight, entity.voteWeight);
	assertEquals(GENDER.name(), entity.gender);
	assertEquals(AUTH_SOURCE.authSource.name(), entity.authSource);
	assertEquals(AUTH_SOURCE.authID, entity.authId);
    }

}
