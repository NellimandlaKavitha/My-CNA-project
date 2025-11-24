package utils;

import org.openqa.selenium.*;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String fileName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("Screenshots/" + fileName + ".png"));
            System.out.println("Screenshot Saved: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}