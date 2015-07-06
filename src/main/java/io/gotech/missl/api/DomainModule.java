package io.gotech.missl.api;

import io.gotech.missl.api.transformers.UserTransformer;
import io.gotech.missl.domain.users.UserBuilder;
import io.gotech.missl.domain.users.UserRepository;
import io.gotech.missl.persistence.users.DatastoreUserRepository;
import io.gotech.missl.persistence.users.UserEntityDTOTransformer;

import com.google.inject.AbstractModule;

public class DomainModule extends AbstractModule {

    @Override
    protected void configure() {
	bind(UserTransformer.class);
	bind(UserBuilder.class);
	bind(UserEntityDTOTransformer.class);
	bind(UserRepository.class).to(DatastoreUserRepository.class);
    }

}
