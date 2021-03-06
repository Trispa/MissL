package io.gotech.missl.persistence.users;

import static com.googlecode.objectify.ObjectifyService.ofy;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserBuilder;
import io.gotech.missl.domain.users.UserDTO;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.domain.users.UserNotFoundException;
import io.gotech.missl.domain.users.UserRepository;
import io.gotech.missl.persistence.UniqueConstraintEnforcer;

import com.google.inject.Inject;
import com.googlecode.objectify.Key;

public class DatastoreUserRepository implements UserRepository {

	private UserEntityDTOTransformer transformer;

	private UserBuilder userBuilder = new UserBuilder();

	private UniqueConstraintEnforcer enforcer;

	@Inject
	public DatastoreUserRepository(UserEntityDTOTransformer transformer,
			UniqueConstraintEnforcer enforcer) {
		this.transformer = transformer;
		this.enforcer = enforcer;
	}

	@Override
	public User findById(UserId userID) {

		UserEntity userEntity = loadEntityFromDataBAse(userID.id);
		if (userEntity == null) {
			String message = String.format("the user with %d not existe",
					userID.id);
			throw new UserNotFoundException(message);
		}
		UserDTO userDTO = transformer.toDTO(userEntity);
		User user = userBuilder.withAuthSource(userDTO.authSource)
				.withFirstName(userDTO.firstName).withUserId(userDTO.id)
				.withVoteWeight(userDTO.voteWeight)
				.withLastName(userDTO.lastName).withSex(userDTO.gender).build();

		return user;
	}

	@Override
	public void addUser(User user) {
		UserDTO userDTO = user.getDTO();
		UserEntity entity = transformer.toEntity(userDTO);
		enforcer.enforceUniqueConstraint(entity, "authSource", "authId");
		Key<UserEntity> key = ofy().save().entity(entity).now();
		user.assignId(new UserId(new Long(key.getId())));

	}

	private UserEntity loadEntityFromDataBAse(long id) {

		Key<UserEntity> key = Key.create(UserEntity.class, id);
		UserEntity userEntity = ofy().load().key(key).now();

		return userEntity;
	}
}
