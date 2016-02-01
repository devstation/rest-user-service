package de.devstation.demo.rus.user;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

  private static final Map<String, User> users = new HashMap<>();

  public Collection<User> getAllUsers() {
    return users.values();
  }

  public User findByName(String name) {
    return users.get(name);
  }

  public void storeNewUser(User user) {
    users.put(user.getName(), user);
  }

}
