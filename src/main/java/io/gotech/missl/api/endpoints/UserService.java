package io.gotech.missl.api.endpoints;

import io.gotech.missl.api.requests.CreateUserRequest;
import io.gotech.missl.constants.Constants;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserBuilder;
import io.gotech.missl.domain.users.UserRepository;

import com.google.api.server.spi.config.Api;
import com.google.inject.Inject;

@Api(name = "missL", version = "v1", clientIds = { Constants.API_EXPLORER_CLIENT_ID })
public class UserService {

    private UserBuilder userBuilder;
    private UserRepository userRepository;

    @Inject
    public UserService(UserBuilder userBuilder, UserRepository userRepository) {
	this.userBuilder = userBuilder;
	this.userRepository = userRepository;
    }

    public User create(CreateUserRequest request) {
	User user = userBuilder.withAuthSource(request.authSource)
		.withFirstName(request.firstName)
		.withLastName(request.lastName).withSex(request.gender).build();
	this.userRepository.saveUser(user);
	return user;
    }
}
