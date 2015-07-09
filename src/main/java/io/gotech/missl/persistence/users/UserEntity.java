package io.gotech.missl.persistence.users;

import org.joda.time.DateTime;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class UserEntity {
    @Id
    public Long id;
    @Index
    public String authSource;
    @Index
    public String authId;
    public String gender;
    public Integer voteWeight;
    public String firstName;
    public String lastName;
    public DateTime dateRegistered;

    public UserEntity(Long id, String authSource, String authId, String gender,
	    Integer voteWeight, String firstName, String lastName,
	    DateTime dateRegistered) {
	this.id = id;
	this.authSource = authSource;
	this.authId = authId;
	this.gender = gender;
	this.voteWeight = voteWeight;
	this.firstName = firstName;
	this.lastName = lastName;
	this.dateRegistered = dateRegistered;
    }

    public UserEntity(Long id, String authSource, String authId, String gender,
	    Integer voteWeight, String firstName, String lastName) {
	this(id, authSource, authId, gender, voteWeight, firstName, lastName,
		null);
    }

}
