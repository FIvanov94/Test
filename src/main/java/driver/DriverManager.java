package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private static WebDriver driver;


    public void setDriver(String binaryPath) {
        System.setProperty("webdriver.chrome.driver", "E:\\Projects\\src\\main\\resources\\chromeDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary(binaryPath);
        options.addArguments("--remote-debugging-port=9222");
        ChromeDriverService chromeservices = new ChromeDriverService.Builder().build();
        chromeservices.getDefaultDriverOptions();
        driver = new ChromeDriver(chromeservices, options);
    }

    public static WebDriver getDriver() {
        return driver;
    }

}
