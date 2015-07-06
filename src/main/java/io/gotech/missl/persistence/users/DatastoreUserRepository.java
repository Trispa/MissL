package io.gotech.missl.persistence.users;

import static com.googlecode.objectify.ObjectifyService.ofy;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserDTO;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.domain.users.UserRepository;

import com.google.inject.Inject;
import com.googlecode.objectify.Key;

public class DatastoreUserRepository implements UserRepository {

    private UserEntityDTOTransformer transformer;

    @Inject
    public DatastoreUserRepository(UserEntityDTOTransformer transformer) {
	this.transformer = transformer;
    }

    @Override
    public User findById(UserId userID) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void saveUser(User user) {
	UserDTO userDTO = user.getDTO();
	UserEntity entity = transformer.toEntity(userDTO);
	Key<UserEntity> key = ofy().save().entity(entity).now();
	user.assignId(new UserId(new Long(key.getId())));

    }
}
