package io.gotech.missl.domain.users;

import io.gotech.missl.domain.elections.VoteWeight;

public class UserDTO {
    public final UserGender gender;
    public final UserId id;
    public final VoteWeight voteWeight;
    public final String firstName;
    public final String lastName;
    public final UserAuthSource authSource;

    public UserDTO(UserId userID, UserGender gender, VoteWeight voteWeight,
	    UserAuthSource authSource, String firstName, String lastName) {
	this.gender = gender;
	this.id = userID;
	this.voteWeight = voteWeight;
	this.firstName = firstName;
	this.lastName = lastName;
	this.authSource = authSource;
    }
}
