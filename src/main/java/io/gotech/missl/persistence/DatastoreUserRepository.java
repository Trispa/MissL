package io.gotech.missl.persistence;

import static com.googlecode.objectify.ObjectifyService.ofy;
import io.gotech.missl.api.transformers.UserTransformer;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserBuilder;
import io.gotech.missl.domain.users.UserDTO;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.domain.users.UserRepository;
import io.gotech.missl.persistence.entities.UserEntity;
import com.google.inject.Inject;
import com.googlecode.objectify.Key;

public class DatastoreUserRepository implements UserRepository {

    private UserEntityDTOTransformer transformer;
  
    private UserBuilder userBuilder;

    @Inject
    public DatastoreUserRepository(UserEntityDTOTransformer transformer, UserBuilder userBuilder) {
	this.transformer = transformer;
	this.userBuilder = userBuilder;
    }

    @Override
    public User findById(UserId userID) {
    
    	UserEntity userEntity = loadEntityFromDataBAse(userID.id);
    	
    	UserDTO userDTO = transformer.toDTO(userEntity);
    	User user = userBuilder.withAuthSource(userDTO.authSource)
    			.withFirstName(userDTO.firstName)
    			.withUserId(userDTO.id)
    			.withVoteWeight(userDTO.voteWeight)
    			.withLastName(userDTO.lastName).withSex(userDTO.gender).build();
    	 
    	return user;
    }

    @Override
    public void saveUser(User user) {
	UserDTO userDTO = user.getDTO();
	UserEntity entity = transformer.toEntity(userDTO);
	Key<UserEntity> key = ofy().save().entity(entity).now();
	user.assignId(new UserId(new Long(key.getId())));

    }
    
    private UserEntity loadEntityFromDataBAse(long id){
    	
    	Key<UserEntity>  key = Key.create(UserEntity.class, id);
		UserEntity userEntity =  (UserEntity) ofy().load().key(key).now();
		
		return userEntity;	
    }
}
