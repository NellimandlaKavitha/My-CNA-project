package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/Features",    
    glue = {"Steps"},
    plugin = {"pretty", "html:target/cucumber-report.html"},  
    monochrome = true,
    tags = "@cnaMultilogin"
)
public class testrunner extends AbstractTestNGCucumberTests {}