package io.gotech.missl.domain.users;

import static org.junit.Assert.assertTrue;
import io.gotech.missl.domain.elections.VoteWeight;

import org.junit.Before;
import org.junit.Test;

public class UserBuilderTest {
    private final UserGender GENDER = UserGender.FEMALE;
    private final VoteWeight USER_VOTE_WEIGHT = new VoteWeight(1);
    private final String FIRST_NAME = "John";
    private final String LAST_NAME = "Doe";
    private final UserAuthSource AUTH_SOURCE = new UserAuthSource(
	    UserAuthSource.Source.FACEBOOK, "1l5f5");

    private User user;

    private UserBuilder userBuilder;

    @Before
    public void initialise() {
	userBuilder = new UserBuilder();
    }

    @Test
    public void whenBuildUserWithoutIdItShouldSetTheDefaultId() {
	user = userBuilder.withAuthSource(AUTH_SOURCE)
		.withFirstName(FIRST_NAME).withLastName(LAST_NAME)
		.withSex(GENDER).withVoteWeight(USER_VOTE_WEIGHT).build();

	assertTrue(user.getId().equals(UserId.NOT_SET));

    }

    @Test(expected = UserBuildError.class)
    public void givenThereAreMissingRequiredAttributeswhenBuildUserItShouldThrowAnException() {
	user = userBuilder.withAuthSource(AUTH_SOURCE)
		.withFirstName(FIRST_NAME).build();
    }
}
