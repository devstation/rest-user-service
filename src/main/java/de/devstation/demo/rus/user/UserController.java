package de.devstation.demo.rus.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.devstation.demo.rus.security.PasswordStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController {

  private static final Logger log = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public HttpEntity<String> create(@RequestBody User user) {
    try {
      userService.storeUser(user);
      return new ResponseEntity<>("OK", HttpStatus.OK);
    } catch (UserService.UserExistsException e) {
      log.info("user creation failed", e);
      return new ResponseEntity<>("user already exists", HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public HttpEntity<Collection<User>> list() {
    return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public HttpEntity<User> byId(@PathVariable int id) {
    return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
  }

  @RequestMapping(value = "/checkpassword", method = RequestMethod.POST)
  public HttpEntity<PasswordStrategy.UserPasswordCheckResult> checkPassword(@RequestBody String password) throws JsonProcessingException {
    return new ResponseEntity<>(userService.checkPassword(password), HttpStatus.OK);
  }

}
