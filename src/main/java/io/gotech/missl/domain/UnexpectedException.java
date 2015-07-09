package io.gotech.missl.domain;

public class UnexpectedException extends RuntimeException {

    private static final long serialVersionUID = -6149367681908882663L;

    public UnexpectedException(Exception exception) {
	super(exception);
    }

}
