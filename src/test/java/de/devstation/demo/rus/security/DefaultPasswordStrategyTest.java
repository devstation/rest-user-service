package de.devstation.demo.rus.security;

import org.junit.Assert;
import org.junit.Test;

public class DefaultPasswordStrategyTest {

  private final PasswordStrategy passwordStrategy = new DefaultPasswordStrategy();

  @Test
  public void checkPassword_ValidPassword_IsValid() {
    String p = "abcDEF123";
    Assert.assertTrue("password not valid, p=", passwordStrategy.checkPassword(p).isValid());
  }

  @Test
  public void checkPassword_PasswordTooShort_IsNotValid() {
    String p = "aB1";
    Assert.assertFalse("password valid, p=" + p, passwordStrategy.checkPassword(p).isValid());
  }

  @Test
  public void checkPassword_PasswordHasNotEnoughLowerCaseChars_IsNotValid() {
    String p = "BCDEFGH123456";
    Assert.assertFalse("password valid, p=" + p, passwordStrategy.checkPassword(p).isValid());
  }

  @Test
  public void checkPassword_PasswordHasNotEnoughUpperCaseChars_IsNotValid() {
    String p = "abcdef123456";
    Assert.assertFalse("password valid, p=" + p, passwordStrategy.checkPassword(p).isValid());
  }

  @Test
  public void checkPassword_PasswordHasNotEnoughNumericChars_IsNotValid() {
    String p = "abcdefGHIJKL";
    Assert.assertFalse("password valid, p=" + p, passwordStrategy.checkPassword(p).isValid());
  }

}
