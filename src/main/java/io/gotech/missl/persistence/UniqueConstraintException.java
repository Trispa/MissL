package io.gotech.missl.persistence;

public class UniqueConstraintException extends RuntimeException {

    private static final long serialVersionUID = -7630197228255516727L;

    public UniqueConstraintException(String message) {
	super(message);
    }

}
