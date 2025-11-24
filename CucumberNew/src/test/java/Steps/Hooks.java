package Steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import utils.ScreenshotUtil;

public class Hooks {

    @Before
    public void setup() {
        Baseclassnew.initialization();
    }

    @After
    public void tearDown(Scenario scenario) {

        
        if (scenario.isFailed()) {
            System.out.println("Scenario failed: Taking screenshot...");

            try {
                ScreenshotUtil.takeScreenshot(Baseclassnew.getDriver(),
                        scenario.getName().replace(" ", "_"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

 
        if (Baseclassnew.getDriver() != null) {
            Baseclassnew.getDriver().quit();
        }
    }
}