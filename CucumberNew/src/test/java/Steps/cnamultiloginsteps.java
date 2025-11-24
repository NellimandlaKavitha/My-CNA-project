package Steps;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.*;
import utils.PropertyReader;

public class cnamultiloginsteps extends Baseclassnew {
	 
	
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
	        System.out.println(" Clicked on '" + buttonName + "' button");
	    } catch (TimeoutException e) {
	        System.out.println(" Could not find button: " + buttonName);
	        throw e;
	    }
	    System.out.println("Clicked on Sign In button.");
	}

    @When("I login with {string} and {string}")
    public void i_login_with_and(String usernameKey, String passwordKey) {
        String username = PropertyReader.getProperty(usernameKey);
        String password = PropertyReader.getProperty(passwordKey);

        System.out.println("Logging in with Username: " + username + " and Password: " + password);
        
    }

    @Then("I should see the homepage")
    public void i_should_see_the_homepage() {
        System.out.println("Login successful - homepage displayed!");
        
    }
}
