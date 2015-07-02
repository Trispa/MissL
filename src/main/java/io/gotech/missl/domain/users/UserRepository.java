package io.gotech.missl.domain.users;

public interface UserRepository {
    public User findById(UserId userID);

    public void saveUser(User user);
}
