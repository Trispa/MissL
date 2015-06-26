package io.gotech.missl.users;

public abstract  class User
{	
	private UserSex sex;

	public User(UserSex userSex){
		this.sex = userSex;	
	}
	
	public  boolean isFemale()
	{
		return this.sex.equals(UserSex.FEMALE);
	}
}
