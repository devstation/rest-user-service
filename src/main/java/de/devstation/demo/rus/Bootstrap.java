package de.devstation.demo.rus;

import de.devstation.demo.rus.user.User;
import de.devstation.demo.rus.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Bootstrap {

  @Autowired
  private UserRepository userRepository;

  @PostConstruct
  public void fillInDummyData() {
    userRepository.storeNewUser(new User("admin", "nimda"));
    userRepository.storeNewUser(new User("user", "resu"));
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Bootstrap.class, args);
  }

}
