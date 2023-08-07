import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/main/resources",
        tags = "@ElectronTests",
        plugin = {
                "pretty",
                "html:target/cucumber/pretty/index.html"
        })
public class Runner extends AbstractTestNGCucumberTests {
}

