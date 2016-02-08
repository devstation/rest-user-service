package de.devstation.demo.rus.user;

import de.devstation.demo.rus.security.PasswordStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordStrategy passwordStrategy;

  public Collection<User> getAllUsers() {
    return userRepository.getAllUsers();
  }

  public User getById(int id) {
    return userRepository.findById(id);
  }

  public User getByName(String name) {
    return userRepository.findByName(name);
  }

  public void storeUser(User user) throws UserExistsException {
    if (user.getId() != User.UNPERSISTED_USER_ID) {
      throw new UserExistsException("user is already persisted, update operation not supported");
    }
    if (getByName(user.getName()) != null) {
      throw new UserExistsException("user with name=" + user.getName() + "exists");
    }
    user.setId(userRepository.nextId());
    userRepository.storeNewUser(user);
  }

  public PasswordStrategy.UserPasswordCheckResult checkPassword(String password) {
    return passwordStrategy.checkPassword(password);
  }

  public static class UserExistsException extends Exception {

    public UserExistsException(String message) {
      super(message);
    }

  }

}
