package io.gotech.missl.domain.users;

public class UserId {
    public final Long id;
    public static final UserId NOT_SET = new UserId(null);

    public UserId(Long id) {
	this.id = id;
    }

    public boolean equals(UserId anotherUserId) {
	if (this.id == null && anotherUserId.id == null) {
	    return true;
	}
	if (this.id != null)
	    return this.id.equals(anotherUserId.id);

	return false;

    }
}
