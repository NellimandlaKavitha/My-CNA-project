package Steps;

import io.cucumber.java.Before;
import io.cucumber.java.After;

public class Hooks {

    @Before
    public void setup() {
        Baseclassnew.initialization();
    }

    @After
    public void tearDown() {
        if (Baseclassnew.getDriver() != null) {
            Baseclassnew.getDriver().quit();
        }
    }
}