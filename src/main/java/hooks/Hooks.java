package hooks;

import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Hooks {

    //   Edit the directory of the application according to your installation directory
    private static final String MUSEEKS = "C:\\Users\\filip\\AppData\\Local\\Programs\\museeks\\Museeks.exe";

    DriverManager driverManager = new DriverManager();


    @Before
    public void setup() {
        driverManager.setDriver(MUSEEKS);
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }


    @After()
    public void takeScreenShot(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.attach(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES), "image/jpeg", "Screenshot_".concat(scenario.getName()));
        }
    }

    @After(order = 9999)
    public void tearDown() {
        DriverManager.getDriver().quit();
    }
}
