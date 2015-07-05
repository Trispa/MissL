package io.gotech.missl.domain.users;

import io.gotech.missl.api.transformers.UserTransformer;
import io.gotech.missl.domain.elections.VoteWeight;
import io.gotech.missl.domain.elections.candidates.Candidate;

import com.google.api.server.spi.config.ApiTransformer;

public class User {
    private UserGender gender;
    private UserId id;
    private VoteWeight voteWeight;
    private String firstName;
    private String lastName;
    private UserAuthSource authSource;

    @ApiTransformer(UserTransformer.class)
    public User(UserId userId, UserGender userSex, VoteWeight weight,
	    UserAuthSource authSource, String firstName, String lastName) {
	this.gender = userSex;
	this.id = userId;
	this.voteWeight = weight;
	this.firstName = firstName;
	this.lastName = lastName;
	this.authSource = authSource;
    }

    public UserId getId() {
	return this.id;
    }

    public void assignId(UserId id) {
	this.id = id;
    }

    public boolean isFemale() {
	return this.gender.equals(UserGender.FEMALE);
    }

    public void vote(Candidate candidate) {
	candidate.receiveVote(id, voteWeight);
    }

    public UserDTO getDTO() {
	return new UserDTO(id, gender, voteWeight, authSource, firstName,
		lastName);
    }

}
