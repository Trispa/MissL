package io.gotech.missl.domain.users;

import io.gotech.missl.domain.elections.VoteWeight;

import java.util.UUID;

public class UserBuilder {
    private UserId userID = null;
    private String firstName = null;
    private String lastName = null;
    private UserGender sex = null;
    private VoteWeight voteWeight = null;
    private UserAuthSource authSource = null;

    public UserBuilder withSex(UserGender sex) {
	this.sex = sex;
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
	if (this.userID == null)
	    this.userID = new UserId(UUID.randomUUID());
	return new User(userID, sex, voteWeight, authSource, firstName,
		lastName);
    }
}
