package io.gotech.missl.api.requests;

import io.gotech.missl.domain.users.UserAuthSource;
import io.gotech.missl.domain.users.UserGender;

public class CreateUserRequest {
    public String firstName;
    public String lastName;
    public UserAuthSource authSource;
    public UserGender gender;

    public CreateUserRequest() {

    }

    public CreateUserRequest(String firstName, String lastName,
	    UserAuthSource authSource, UserGender gender) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.authSource = authSource;
	this.gender = gender;
    }
}
