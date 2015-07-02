package io.gotech.missl.api.endpoints;

import io.gotech.missl.api.requests.CreateUserRequest;
import io.gotech.missl.api.responses.UserInfo;

import com.google.api.server.spi.config.Api;

@Api(name = "missL", version = "v1")
public class UserService {
    public UserInfo create(CreateUserRequest request) {
	throw new RuntimeException("Not yet Implemented");
    }
}
