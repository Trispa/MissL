package io.gotech.missl.domain.users;

import io.gotech.missl.domain.elections.VoteWeight;

public class UserBuilder {
    private UserId userID = UserId.NOT_SET;
    private String firstName = null;
    private String lastName = null;
    private UserGender gender = null;
    private VoteWeight voteWeight = null;
    private UserAuthSource authSource = null;

    public UserBuilder withSex(UserGender gender) {
	this.gender = gender;
	return this;
    }

    public UserBuilder withFirstName(String firstName) {
	this.firstName = firstName;
	return this;
    }

    public UserBuilder withLastName(String lastName) {
	this.lastName = lastName;
	return this;
    }

    public UserBuilder withVoteWeight(VoteWeight voteWeight) {
	this.voteWeight = voteWeight;
	return this;
    }

    public UserBuilder withUserId(UserId userID) {
	this.userID = userID;
	return this;
    }

    public UserBuilder withAuthSource(UserAuthSource authSource) {
	this.authSource = authSource;
	return this;
    }

    public User build() {
	if ((firstName == null) || (lastName == null) || (authSource == null)
		|| (gender == null)) {
	    throw new UserBuildError(
		    "There are missing user attributes. The attributes firstName, lastName, authSource and gender are required to create a user");
	}
	return new User(userID, gender, voteWeight, authSource, firstName,
		lastName);
    }
}
