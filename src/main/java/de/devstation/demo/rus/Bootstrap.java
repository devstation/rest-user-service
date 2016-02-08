package de.devstation.demo.rus;

import de.devstation.demo.rus.user.User;
import de.devstation.demo.rus.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Bootstrap {

  @Autowired
  private UserService userService;

  @PostConstruct
  public void fillInDummyData() throws UserService.UserExistsException {
    userService.storeUser(new User("admin", "nimda"));
    userService.storeUser(new User("user", "resu"));
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Bootstrap.class, args);
  }

}
