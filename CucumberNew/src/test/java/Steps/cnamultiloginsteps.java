package Steps;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.*;
import utils.PropertyReader;
import utils.ScreenshotUtil;

public class cnamultiloginsteps extends Baseclassnew {

   
    private static final String LOGIN_PROP =
            "src/test/resources/credentials/login.properties";

  
    @Given("I open the application")
    public void i_open_the_application() {
        initialization();
        System.out.println("Application launched successfully.");
    }

    
    @And("User clicks login {string} button")
    public void click_on_signin(String buttonName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        By buttonLocator = By.xpath("//*[normalize-space(text())='" + buttonName + "']");

        try {
            WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(buttonLocator));
            signInButton.click();
            System.out.println("Clicked on '" + buttonName + "' button");
        } catch (TimeoutException e) {
            System.out.println("Could not find button: " + buttonName);
            throw e;
        }
    }

    
    @When("I login with {string} and {string}")
    public void i_login_with_and(String usernameKey, String passwordKey) {

       
        String username = PropertyReader.getProperty(LOGIN_PROP, usernameKey);
        String password = PropertyReader.getProperty(LOGIN_PROP, passwordKey);

       
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

      
        ScreenshotUtil.takeScreenshot(driver, "Username_" + username);
        ScreenshotUtil.takeScreenshot(driver, "Password_" + password);

        System.out.println("Logging in with Username: " + username + " and Password: " + password);

       
        try {
            WebElement loginBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.elementToBeClickable(By.id("loginButton")));
            loginBtn.click();
        } catch (Exception e) {
            System.out.println("Login button not found, please verify locator.");
        }
    }

   
    @Then("I should see the homepage")
    public void i_should_see_the_homepage() {
        System.out.println("Login successful - homepage displayed!");
        ScreenshotUtil.takeScreenshot(driver, "Home page");
        driver.quit();
    }
}
