package io.gotech.missl.persistence;

import io.gotech.missl.persistence.users.UserEntity;

import org.junit.After;
import org.junit.Before;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.impl.translate.opt.joda.JodaTimeTranslators;
import com.googlecode.objectify.util.Closeable;

public class PersistenceTest {
    /**
     * Implementation of the local service
     */
    protected Closeable session;
    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
	    new LocalDatastoreServiceTestConfig());

    /**
     * keeps all the local data in memory
     */
    @Before
    public void setUp() {
	JodaTimeTranslators.add(ObjectifyService.factory());
	ObjectifyService.register(UserEntity.class);

	helper.setUp();
	session = ObjectifyService.begin();

    }

    /**
     * Wipes out all the local data after the test
     */
    @After
    public void tearDown() {
	this.session.close();
	helper.tearDown();
    }

}
