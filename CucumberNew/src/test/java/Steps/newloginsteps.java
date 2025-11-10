package Steps;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;
import io.cucumber.java.en.*;

public class newloginsteps extends Baseclassnew {

    SoftAssert softassert = new SoftAssert();

    @Given("Browser is open")
    public void browser_is_open() {
    
       System.out.println("Browser initialized");
    }

    @And("user is on login page")
    public void user_is_on_login_page() {
       
        initialization();
        driver.navigate().to("https://practicetestautomation.com/practice-test-login/");
        String currenturl = driver.getCurrentUrl();
        String ExpectedURL = "https://practicetestautomation.com/practice-test-login/";
        System.out.println("Current URL:"+currenturl);

        softassert.assertEquals(currenturl, ExpectedURL, "User not launched Application");
    }

    @When("^user enters (.+) and (.+)$")
    public void user_enters_username_and_password(String username, String password) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

        driver.findElement(By.id("username")).sendKeys(username.trim());
        driver.findElement(By.id("password")).sendKeys(password.trim());
    }

    @And("user click on login")
    public void user_click_on_login() {
        driver.findElement(By.id("submit")).click();
    }

    @Then("user is navigated to the home page")
    public void user_is_navigated_to_the_home_page() {
        boolean status = driver.getPageSource().contains("Logged In Successfully");
        softassert.assertTrue(status, "Login failed");
        softassert.assertAll();
    }
}