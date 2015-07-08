package io.gotech.missl.persistence;

import io.gotech.missl.domain.elections.VoteWeight;
import io.gotech.missl.domain.users.UserAuthSource;
import io.gotech.missl.domain.users.UserGender;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.persistence.users.UserEntity;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class UniqueConstraintEnforcerTest extends PersistenceTest {
    protected final LocalServiceTestHelper helper = new LocalServiceTestHelper(
	    new LocalDatastoreServiceTestConfig()
		    .setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

    private final UserId USER_ID = UserId.NOT_SET;
    private final VoteWeight USER_VOTE_WEIGHT = new VoteWeight(1);
    private final UserGender GENDER = UserGender.FEMALE;
    private final String FIRST_NAME = "John";
    private final String LAST_NAME = "Doe";
    private final UserAuthSource AUTH_SOURCE = new UserAuthSource(
	    UserAuthSource.Source.FACEBOOK, "1l5f5");

    private UniqueConstraintEnforcer uniqueConstraintEnforcer;
    private UserEntity userEntity;

    @Before
    public void initialise() {
	userEntity = new UserEntity(USER_ID.id, AUTH_SOURCE.authSource.name(),
		AUTH_SOURCE.authID, GENDER.name(), USER_VOTE_WEIGHT.weight,
		FIRST_NAME, LAST_NAME);
	uniqueConstraintEnforcer = new UniqueConstraintEnforcer();
	helper.setUp();

    }

    @Test(expected = UniqueConstraintException.class)
    public void givenAlreadyEnforcedConstraintEnforcingAgainForTheSameValuesShouldRaiseAnException()
	    throws NoSuchFieldException, SecurityException,
	    IllegalArgumentException, IllegalAccessException {
	uniqueConstraintEnforcer.enforceUniqueConstraint(userEntity,
		"authSource", "authId");
	uniqueConstraintEnforcer.enforceUniqueConstraint(userEntity,
		"authSource", "authId");
    }

}
