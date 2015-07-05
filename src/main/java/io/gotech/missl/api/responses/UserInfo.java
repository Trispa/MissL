package io.gotech.missl.api.responses;


public class UserInfo {
    public final Long id;
    public final String firstName;
    public final String lastName;
    public final Integer voteWeight;

    public UserInfo(Long id, String firstName, String lastName,
	    Integer voteWeight) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.voteWeight = voteWeight;
    }
}
