package io.gotech.missl.persistence.users;



import io.gotech.missl.domain.elections.VoteWeight;
import io.gotech.missl.domain.users.UserAuthSource;
import io.gotech.missl.domain.users.UserDTO;
import io.gotech.missl.domain.users.UserGender;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.persistence.EntityDTOTransformer;

public class UserEntityDTOTransformer implements
	EntityDTOTransformer<UserEntity, UserDTO> {

    @Override
    public UserEntity toEntity(UserDTO dto) {
	return new UserEntity(dto.id.id, dto.authSource.authSource.name(),
		dto.authSource.authID, dto.gender.name(),
		dto.voteWeight.weight, dto.firstName, dto.lastName);
    }

    @Override
    public UserDTO toDTO(UserEntity entity) {
	UserId id = new UserId(entity.id);
	UserGender gender = UserGender.valueOf(entity.gender);
	VoteWeight voteWeight = new VoteWeight(entity.voteWeight);
	UserAuthSource authSource = new UserAuthSource(
		UserAuthSource.Source.valueOf(entity.authSource), entity.authId);

	return new UserDTO(id, gender, voteWeight, authSource,
		entity.firstName, entity.lastName);
    }
    
    

}
