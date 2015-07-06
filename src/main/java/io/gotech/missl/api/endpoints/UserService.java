package io.gotech.missl.api.endpoints;

import io.gotech.missl.api.requests.CreateUserRequest;
import io.gotech.missl.constants.Constants;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserBuilder;
import io.gotech.missl.domain.users.UserRepository;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.inject.Inject;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;

@Api(name = "missL", version = "v1", clientIds = { Constants.API_EXPLORER_CLIENT_ID })
public class UserService {

    private UserBuilder userBuilder;
    private UserRepository userRepository;

    @Inject
    public UserService(UserBuilder userBuilder, UserRepository userRepository) {
	this.userBuilder = userBuilder;
	this.userRepository = userRepository;
    }

    @ApiMethod(httpMethod = HttpMethod.POST)
    public User create(CreateUserRequest request) {
	Closeable session = ObjectifyService.begin();
	User user = userBuilder.withAuthSource(request.authSource)
		.withFirstName(request.firstName)
		.withLastName(request.lastName).withSex(request.gender).build();
	this.userRepository.saveUser(user);
	session.close();
	return user;
    }
}
