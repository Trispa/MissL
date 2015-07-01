package io.gotech.missl.domain.users;

import java.util.UUID;

public class UserId
{
	private UUID uuid;
	
	public UserId(UUID userUUID) {
		this.uuid = userUUID;
	}
	
	public boolean equals(UserId anotherUserId) {
		return uuid.equals(anotherUserId.uuid);
	}
}
