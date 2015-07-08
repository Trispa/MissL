package io.gotech.missl.persistence;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.lang.reflect.Field;

import com.googlecode.objectify.Work;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

public class UniqueConstraintEnforcer {

    @Entity
    public static class UniqueConstraint {
	@Id
	private String key;

	public void setKey(String value) {
	    this.key = value;
	}

	public String getKeyString() {
	    return this.key;
	}
    }

    public void enforceUniqueConstraint(Object object, String... fieldnames)
	    throws NoSuchFieldException, SecurityException,
	    IllegalArgumentException, IllegalAccessException {
	final String uniqueConstraintKey = this.getKeyFromObject(object,
		fieldnames);
	final UniqueConstraint newUniqueConstraint = new UniqueConstraint();
	newUniqueConstraint.setKey(uniqueConstraintKey);
	boolean transactionSuccess = ofy().transact(new Work<Boolean>() {
	    public Boolean run() {
		UniqueConstraint aConstraint = ofy().load()
			.type(UniqueConstraint.class).id(uniqueConstraintKey)
			.now();
		if (aConstraint == null) {
		    ofy().save().entity(newUniqueConstraint).now();
		    return new Boolean(true);
		}
		return new Boolean(false);
	    }
	}).booleanValue();

	if (!transactionSuccess) {
	    throw new UniqueConstraintException(
		    "There is already a unique constraint with those fields for this object");
	}
    }

    private String getKeyFromObject(Object object, String... fieldnames)
	    throws NoSuchFieldException, SecurityException,
	    IllegalArgumentException, IllegalAccessException {
	StringBuilder builder = new StringBuilder();
	Class<?> objectClass = object.getClass();
	builder.append(objectClass.toString());
	for (String fieldname : fieldnames) {
	    builder.append(":");
	    Field field = objectClass.getField(fieldname);
	    Object fieldValue = field.get(object);
	    builder.append(fieldname).append("-").append(fieldValue.toString());
	}
	return builder.toString();
    }

}
