package io.gotech.missl.api.responses;

import java.util.UUID;

public class UserInfo {
    public final UUID id;
    public final String firstName;
    public final String lastName;
    public final Integer voteWeight;

    public UserInfo(UUID id, String firstName, String lastName,
	    Integer voteWeight) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.voteWeight = voteWeight;
    }
}
