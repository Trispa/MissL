package io.gotech.missl.domain.users;

import java.util.UUID;

public class UserId {
    final public UUID uuid;

    public UserId(UUID userUUID) {
	this.uuid = userUUID;
    }

    public boolean equals(UserId anotherUserId) {
	return uuid.equals(anotherUserId.uuid);
    }
}
