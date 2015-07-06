package io.gotech.missl.api.transformers;

import io.gotech.missl.api.responses.UserInfo;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserDTO;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.domain.users.UserRepository;

import com.google.api.server.spi.config.Transformer;
import com.google.inject.Inject;

public class UserTransformer implements Transformer<User, UserInfo> {

    @Inject
    private static UserRepository userRepository;

    @Inject
    public UserTransformer(UserRepository userRepo) {
	userRepository = userRepo;
    }

    public UserTransformer() {

    }

    @Override
    public User transformFrom(UserInfo userInfo) {
	return userRepository.findById(new UserId(userInfo.id));
    }

    @Override
    public UserInfo transformTo(User user) {
	UserDTO userDTO = user.getDTO();
	return new UserInfo(userDTO.id.id, userDTO.firstName, userDTO.lastName,
		userDTO.voteWeight.weight);
    }
}
