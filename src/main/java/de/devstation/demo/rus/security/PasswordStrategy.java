package de.devstation.demo.rus.security;

import java.util.List;

public interface PasswordStrategy {

  int getMinChars();

  int getMinLowerCaseChars();

  int getMinUpperCaseChars();

  int getMinNumericChars();

  UserPasswordCheckResult checkPassword(String password);

  class UserPasswordCheckResult {
    private final boolean valid;
    private final List<String> messages;

    UserPasswordCheckResult(boolean valid, List<String> messages) {
      this.valid = valid;
      this.messages = messages;
    }

    public boolean isValid() {
      return valid;
    }

    public List<String> getMessages() {
      return messages;
    }
  }

}
