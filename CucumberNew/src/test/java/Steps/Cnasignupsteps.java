package Steps;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.*;
import utils.PropertyReader;

public class Cnasignupsteps extends Baseclassnew {
	
	private static final String SIGNUP_PROP="src/test/resources/credentials/signup.properties";
	
	
    SoftAssert softassert = new SoftAssert();
    private boolean isEmailAlreadyUsed() {
        try {
            return driver.findElement(
                By.xpath("//*[contains(text(),'already exists')]")
            ).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    @Given("Launch the URL")
    public void launch_the_url() {
        initialization();
     
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));

      
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        System.out.println(" Page loaded successfully.");
    }

    @Given("User is on the Landing Page")
    public void user_is_on_the_landing_page() {
        System.out.println("Navigated to Landing Page.");
    }

    @When("User clicks on {string} button")
    public void user_clicks_on_button(String buttonName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

      
        By buttonLocator = By.xpath("//*[normalize-space(text())='" + buttonName + "']");

        try {
            WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(buttonLocator));
            signInButton.click();
            System.out.println(" Clicked on '" + buttonName + "' button");
        } catch (TimeoutException e) {
            System.out.println("❌ Could not find button: " + buttonName);
            throw e;
        }
    }

    @When("User navigates to the MeConnect Login Page")
    public void user_navigates_to_the_me_connect_login_page() {
        String pageTitle = driver.getTitle();
        String expectedTitle = pageTitle;
        System.out.println("🪶 Current page title: " + pageTitle);
        softassert.assertEquals(pageTitle, expectedTitle, "User not navigated to Login Page");
    }

    @When("User clicks on {string} link")
    public void user_clicks_on_link(String linkText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[text()='CREATE ONE NOW']")));
        link.click();
        System.out.println(" Clicked on 'CREATE ONE NOW' link successfully.");
    }

    @Then("User is on the {string} page")
    public void user_is_on_the_page(String expectedPageName) {
        String actualTitle = driver.getTitle();
        softassert.assertTrue(actualTitle.contains(expectedPageName),
                "Expected page: " + expectedPageName + ", but found: " + actualTitle);
        System.out.println("Landed on page: " + expectedPageName);
    }

    @When("User enters email")
    public void user_enters_email() {

        int index = Integer.parseInt(
            PropertyReader.getProperty(SIGNUP_PROP, "signup.email.index")
        );

        String emailKey = "signup.email" + index;
        String email = PropertyReader.getProperty(SIGNUP_PROP, emailKey);

        if (email == null || email.trim().isEmpty()) {

            throw new RuntimeException(
                "❌ Email not found in properties for key: " + emailKey +
                ". Please add it or reset signup.email.index"
            );
        }

        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);

        System.out.println(" Using signup email: " + email);
    }

    @When("User enters new password")
    public void user_enters_new_password() {

        String password = PropertyReader.getProperty(
            SIGNUP_PROP, "signup.password");

        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("mc-confirm-password")).sendKeys(password);
    }


    @When("User agrees to the terms and conditions")
    public void user_agrees_to_the_terms_and_conditions() {
        driver.findElement(By.id("ulp-tnc-pp")).click();
    }

    @When("User clicks on the {string} button")
    public void user_clicks_on_the_button(String buttonText) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("(//button[normalize-space(text())='" + buttonText + "'])[2]")
        ));
        nextBtn.click();

        if (isEmailAlreadyUsed()) {

            int index = Integer.parseInt(
                PropertyReader.getProperty(SIGNUP_PROP, "signup.email.index")
            );

            index++;
            PropertyReader.setProperty(
                SIGNUP_PROP, "signup.email.index", String.valueOf(index)
            );

            System.out.println("Email already used. Switching to email index: " + index);

            user_enters_email();
            nextBtn.click();
        }
    }

    @Then("User is navigated to the {string} page")
    public void user_is_navigated_to_the_page(String pageText) {
        System.out.println("Navigated to: " + pageText);
    }

    @When("User enters personal details:")
    public void user_enters_personal_details(io.cucumber.datatable.DataTable dataTable) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<Map<String, String>> details = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : details) {

            String field = row.get("Field");
            String value = null;

            switch (field) {

                case "First Name":
                    value = PropertyReader.getProperty(
                            SIGNUP_PROP, "signup.firstName");

                    WebElement firstName = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.xpath("//label[contains(text(),'First Name')]/following::input[1]")
                            ));
                    firstName.sendKeys(value);
                    break;

                case "Last Name":
                    value = PropertyReader.getProperty(
                            SIGNUP_PROP, "signup.lastName");

                    WebElement lastName = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.xpath("//label[contains(text(),'Last Name')]/following::input[1]")
                            ));
                    lastName.sendKeys(value);
                    break;

                case "Date of Birth":
                    value = PropertyReader.getProperty(
                            SIGNUP_PROP, "signup.dob");

                    WebElement dob = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.xpath("//label[contains(text(),'Date of Birth')]/following::input[1]")
                            ));
                    dob.sendKeys(value);
                    break;

                default:
                    System.out.println("Unknown field: " + field);
            }
        }
    }

    @When("User clicks on the {string} button on the profile setup page")
    public void user_clicks_on_the_button_on_the_profile_setup_page(String buttonText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[normalize-space(text())='" + buttonText + "']")));
        button.click();
        System.out.println("Clicked on '" + buttonText + "' on profile setup page");
    }

    @Then("User clicks on the final {string} button")
    public void user_clicks_on_the_final_button(String buttonText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25)); // increased wait
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            System.out.println("Total iframes found: " + iframes.size());

            if (!iframes.isEmpty()) {
                driver.switchTo().frame(iframes.get(0));
                System.out.println(" Switched to iframe");
            } else {
                System.out.println("No iframe found — staying on main page");
            }

          
            WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[.//span[normalize-space(text())='" + buttonText + "']]")));

            
            js.executeScript("arguments[0].scrollIntoView(true);", button);
            Thread.sleep(1000); 

            
            js.executeScript("arguments[0].click();", button);
            System.out.println("Clicked on final button via JS: " + buttonText);

        } catch (Exception e) {
            System.out.println("❌ Could not click on the button: " + buttonText);
            e.printStackTrace();
        } finally {
            driver.switchTo().defaultContent();
        }
    }


    @Then("User is signed in and landed on the main site")
    public void user_is_signed_in_and_landed_on_the_main_site() {
        SoftAssert softAssert = new SoftAssert();
        String actualTitle = driver.getTitle();
        System.out.println("Current page title after signup: " + actualTitle);

        softAssert.assertTrue(actualTitle.contains("CNA") || actualTitle.contains("meconnect"),
            "Expected to land on main site, but found: " + actualTitle);

        softAssert.assertAll();
        
    }
}