package io.gotech.missl.persistence.users;

import static com.googlecode.objectify.ObjectifyService.ofy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import io.gotech.missl.domain.elections.VoteWeight;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserAuthSource;
import io.gotech.missl.domain.users.UserBuilder;
import io.gotech.missl.domain.users.UserDTO;
import io.gotech.missl.domain.users.UserGender;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.persistence.PersistenceTest;
import io.gotech.missl.persistence.UniqueConstraintEnforcer;
import io.gotech.missl.persistence.UniqueConstraintException;

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

	private UserBuilder userBuilder = new UserBuilder();
	@Mock
	private User userWithSameAuthSource;
	@Mock
	private UserDTO userWithSameAuthSourceDTO;
	@Mock
	private UserDTO userDTO;
	@Mock
	private UniqueConstraintEnforcer enforcer;

	private UserEntity userEntity;
	private DatastoreUserRepository repository;

	@Before
	public void initialise() {
		Mockito.when(user.getDTO()).thenReturn(userDTO);

		Mockito.when(transformer.toDTO(Mockito.any(UserEntity.class)))
				.thenCallRealMethod();
		userEntity = new UserEntity(USER_ID.id, AUTH_SOURCE.authSource.name(),
				AUTH_SOURCE.authID, GENDER.name(), USER_VOTE_WEIGHT.weight,
				FIRST_NAME, LAST_NAME);
		Mockito.when(transformer.toEntity(userDTO)).thenReturn(userEntity);

		Mockito.when(userWithSameAuthSource.getDTO()).thenReturn(
				userWithSameAuthSourceDTO);
		UserEntity userEntityWithSameAuthSource = new UserEntity(USER_ID.id,
				AUTH_SOURCE.authSource.name(), AUTH_SOURCE.authID,
				UserGender.MALE.name(), USER_VOTE_WEIGHT.weight, "Jeanne",
				"D'arc");
		Mockito.when(transformer.toEntity(userWithSameAuthSourceDTO))
				.thenReturn(userEntityWithSameAuthSource);

		Mockito.doThrow(UniqueConstraintException.class)
				.when(enforcer)
				.enforceUniqueConstraint(userEntityWithSameAuthSource,
						"authSource", "authId");

		repository = new DatastoreUserRepository(transformer, enforcer);

	}

	@Test
	public void givenAUserWhenAddUserItSavesTheUserToTheDataStore() {
		repository.addUser(user);
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

	@Test
	public void WhenAddUserItShouldCheckVerifyTheUniqueConstraintOnAuthSource() {
		repository.addUser(user);
		Mockito.verify(enforcer).enforceUniqueConstraint(userEntity,
				"authSource", "authId");
	}

	@Test(expected = UniqueConstraintException.class)
	public void whenAddUserWithAnExistingAuthSourceItShouldRaiseAnException() {
		repository.addUser(user);
		repository.addUser(userWithSameAuthSource);
	}

	@Test
	public void verifyThatUsergetedHaveTheExpectedUserID() throws Exception {

		Mockito.when(user.getId()).thenCallRealMethod();
		Mockito.doCallRealMethod().when(user)
				.assignId(Mockito.any(UserId.class));
		repository.addUser(user);

		User userFound = repository.findById(user.getId());
		assertNotNull(userFound);

	}

}
