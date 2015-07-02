package io.gotech.missl.api.transformers;

import io.gotech.missl.api.responses.UserInfo;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserDTO;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.domain.users.UserRepository;

import com.google.api.server.spi.config.Transformer;

public class UserTransformer implements Transformer<User, UserInfo> {

    private UserRepository userRepository;

    public UserTransformer(UserRepository userRepository) {
	this.userRepository = userRepository;
    }

    @Override
    public User transformFrom(UserInfo userInfo) {
	return userRepository.findById(new UserId(userInfo.id));
    }

    @Override
    public UserInfo transformTo(User user) {
	UserDTO userDTO = user.getDTO();
	return new UserInfo(userDTO.id.uuid, userDTO.firstName,
		userDTO.lastName, userDTO.voteWeight.weight);
    }
}
