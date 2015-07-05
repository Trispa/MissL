package io.gotech.missl.api;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class ApiGuiceServletContextListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {

	return Guice.createInjector(new ApiServiceModule(), new DomainModule());

    }

}
