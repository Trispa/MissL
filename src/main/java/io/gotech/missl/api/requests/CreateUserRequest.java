package io.gotech.missl.api.requests;

import io.gotech.missl.domain.users.UserAuthSource;
import io.gotech.missl.domain.users.UserGender;

public class CreateUserRequest {
    public String firstName;
    public String lastName;
    public UserAuthSource authSource;
    public UserGender sex;
}
