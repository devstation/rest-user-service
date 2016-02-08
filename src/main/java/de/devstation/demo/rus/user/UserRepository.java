package de.devstation.demo.rus.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository {

  private static final Logger log = LoggerFactory.getLogger(UserRepository.class);

  private static final Map<Integer, User> usersById = new HashMap<>();
  private static final Map<String, User> usersByName = new HashMap<>();
  private AtomicInteger nextId = new AtomicInteger(1);

  public Collection<User> getAllUsers() {
    return usersById.values();
  }

  public User findById(int id) {
    return usersById.get(id);
  }

  public User findByName(String name) {
    return usersByName.get(name);
  }

  private int nextId() {
    return nextId.getAndIncrement();
  }

  public void resetId() {
    nextId = new AtomicInteger(1);
  }

  public void clearUsers() {
    usersById.clear();
    usersByName.clear();
  }

  public User storeNewUser(User user) throws UserService.UserExistsException {
    if (user.getId() != User.UNPERSISTED_USER_ID) {
      throw new UserService.UserExistsException("user is already persisted, update operation not supported");
    }
    user.setId(nextId());
    usersById.put(user.getId(), user);
    usersByName.put(user.getName(), user);
    return user;
  }

}
