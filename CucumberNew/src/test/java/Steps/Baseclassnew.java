package Steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclassnew {

    public static WebDriver driver;

    public static void initialization() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.channelnewsasia.com/international");
      

    }

    public static WebDriver getDriver() {
        return driver;
    }
}

