package de.devstation.demo.rus.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Qualifier("passwordStrategy")
public class DefaultPasswordStrategy implements PasswordStrategy {

  @Override
  public int getMinChars() {
    return 8;
  }

  @Override
  public int getMinLowerCaseChars() {
    return 1;
  }

  @Override
  public int getMinUpperCaseChars() {
    return 1;
  }

  @Override
  public int getMinNumericChars() {
    return 1;
  }

  @Override
  public UserPasswordCheckResult checkPassword(String password) {
    if (password == null || password.isEmpty()) {
      return new UserPasswordCheckResult(false, Arrays.asList("password is empty!"));
    }

    List<String> messages = new ArrayList<>();
    if (password.length() < getMinChars()) {
      messages.add("* at least " + getMinChars() + " char(s) required at all");
    }
    if (!checkLowerCaseCharsMatch(password)) {
      messages.add("* at least " + getMinLowerCaseChars() + " lower case char(s) required");
    }
    if (!checkUpperCaseCharsMatch(password)) {
      messages.add("* at least " + getMinUpperCaseChars() + " upper case char(s) required");
    }
    if (!checkNumericCharsMatch(password)) {
      messages.add("* at least " + getMinNumericChars() + " numeric char(s) required");
    }

    if (messages.isEmpty()) {
      return new UserPasswordCheckResult(true, Arrays.asList("password is valid"));
    }
    return new UserPasswordCheckResult(false, messages);
  }

  private boolean checkLowerCaseCharsMatch(String password) {
    return checkPatternMatch(password, "[a-z]{" + getMinLowerCaseChars() + "}");
  }

  private boolean checkUpperCaseCharsMatch(String password) {
    return checkPatternMatch(password, "[A-Z]{" + getMinUpperCaseChars() + "}");
  }

  private boolean checkNumericCharsMatch(String password) {
    return checkPatternMatch(password, "[0-9]{" + getMinNumericChars() + "}");
  }

  private static boolean checkPatternMatch(String password, String pattern) {
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(password);
    return m.find();
  }

}
