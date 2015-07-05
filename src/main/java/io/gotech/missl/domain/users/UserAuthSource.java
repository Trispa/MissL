package io.gotech.missl.domain.users;

public class UserAuthSource {

    public enum Source {
	FACEBOOK
    }

    public final Source authSource;
    public final String authID;

    public UserAuthSource(Source authSource, String authID) {
	this.authSource = authSource;
	this.authID = authID;
    }

    public boolean equals(UserAuthSource anotherUserAuthSource) {
	return this.authSource.equals(anotherUserAuthSource.authSource)
		&& this.authID == anotherUserAuthSource.authID;
    }

}
