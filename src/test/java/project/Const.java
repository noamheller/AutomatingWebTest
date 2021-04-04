package project;
import org.openqa.selenium.By;

/**
 * constant locators to use in tests
 */
public class Const {
   public static final By LOG_IN_HOME_LOCATOR = By.className("seperator-link");
   public static final By REGISTER_FORM_LOCATOR = By.cssSelector("form[action=register]");
   public static final By OPTIONS_LOCATOR = By.tagName("li");
   public static final By SEARCH_GIFT_LOCATOR = By.cssSelector("a[rel=nofollow]");
   public static final By BUTTON_LOCATOR = By.tagName("button");
   public static final By INPUT_LOCATOR = By.tagName("input");
   public static final By SENDER_NAME_LOCATOR = By.tagName("ul");



}
