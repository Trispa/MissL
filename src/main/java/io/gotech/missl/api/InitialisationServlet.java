package io.gotech.missl.api;

import io.gotech.missl.persistence.UniqueConstraintEnforcer;
import io.gotech.missl.persistence.users.UserEntity;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.impl.translate.opt.joda.JodaTimeTranslators;

public class InitialisationServlet implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0) {

    }

    public void contextInitialized(ServletContextEvent arg0) {
	registerObjectifyEntities();
    }

    private void registerObjectifyEntities() {
	JodaTimeTranslators.add(ObjectifyService.factory());
	ObjectifyService.register(UserEntity.class);
	ObjectifyService
		.register(UniqueConstraintEnforcer.UniqueConstraint.class);
    }

}
