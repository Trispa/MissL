package io.gotech.missl.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import io.gotech.missl.domain.elections.VoteWeight;
import io.gotech.missl.domain.users.UserAuthSource;
import io.gotech.missl.domain.users.UserDTO;
import io.gotech.missl.domain.users.UserGender;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.persistence.entities.UserEntity;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class UserEntityDTOTransformerTest {
    private final UserId USER_ID = new UserId(new Long(4));
    private final VoteWeight USER_VOTE_WEIGHT = new VoteWeight(1);
    private final UserGender GENDER = UserGender.FEMALE;
    private final String FIRST_NAME = "John";
    private final String LAST_NAME = "Doe";
    private final UserAuthSource AUTH_SOURCE = new UserAuthSource(
	    UserAuthSource.Source.FACEBOOK, "1l5f5");
    private final DateTime DATE_REGISTERED = DateTime.now();

    private UserEntity userEntity;
    private UserEntity userEntityTransformedFromDTO;
    private UserDTO userDTO;
    private UserDTO userDTOTransformedFromEntity;
    private UserEntityDTOTransformer transformer;

    @Before
    public void initialise() {
	userEntity = new UserEntity(USER_ID.id, AUTH_SOURCE.authSource.name(),
		AUTH_SOURCE.authID, GENDER.name(), USER_VOTE_WEIGHT.weight,
		FIRST_NAME, LAST_NAME, DATE_REGISTERED);
	userDTO = new UserDTO(USER_ID, GENDER, USER_VOTE_WEIGHT, AUTH_SOURCE,
		FIRST_NAME, LAST_NAME);
	transformer = new UserEntityDTOTransformer();
    }

    @Test
    public void whenToEntityItTransformsDTOToEntity() {
	userEntityTransformedFromDTO = transformer.toEntity(userDTO);
	verifyThatExpectedEntityReturned();
    }

    private void verifyThatExpectedEntityReturned() {
	assertEquals(USER_ID.id, userEntityTransformedFromDTO.id);
	assertEquals(AUTH_SOURCE.authSource.name(),
		userEntityTransformedFromDTO.authSource);
	assertEquals(AUTH_SOURCE.authID, userEntityTransformedFromDTO.authId);
	assertEquals(GENDER.name(), userEntityTransformedFromDTO.gender);
	assertEquals(USER_VOTE_WEIGHT.weight,
		userEntityTransformedFromDTO.voteWeight);
	assertEquals(FIRST_NAME, userEntityTransformedFromDTO.firstName);
	assertEquals(LAST_NAME, userEntityTransformedFromDTO.lastName);
    }

    @Test
    public void whenToDTOItTransformsEntityToDTO() {
	userDTOTransformedFromEntity = transformer.toDTO(userEntity);
	verifyThatExpectedDTOReturned();

    }

    private void verifyThatExpectedDTOReturned() {
	assertTrue(userDTOTransformedFromEntity.id.equals(USER_ID));
	assertTrue(userDTOTransformedFromEntity.authSource.equals(AUTH_SOURCE));
	assertEquals(GENDER, userDTOTransformedFromEntity.gender);
	assertTrue(userDTOTransformedFromEntity.voteWeight
		.equals(USER_VOTE_WEIGHT));
	assertEquals(FIRST_NAME, userDTOTransformedFromEntity.firstName);
	assertEquals(LAST_NAME, userDTOTransformedFromEntity.lastName);
    }

}
