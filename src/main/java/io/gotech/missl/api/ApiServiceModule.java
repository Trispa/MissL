package io.gotech.missl.api;

import io.gotech.missl.api.endpoints.UserService;

import java.util.HashSet;
import java.util.Set;

import com.google.api.server.spi.guice.GuiceSystemServiceServletModule;

public class ApiServiceModule extends GuiceSystemServiceServletModule {
    @Override
    protected void configureServlets() {
	super.configureServlets();

	Set<Class<?>> serviceClasses = new HashSet<Class<?>>();
	serviceClasses.add(UserService.class);

	this.serveGuiceSystemServiceServlet("/_ah/spi/*", serviceClasses);
    }
}
