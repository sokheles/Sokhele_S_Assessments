package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@run",
        features = "src/test/resources/API_Tests",
        glue = {"stepDefinitions"},
        plugin = { "pretty", "html:Results/cucumber-reports" },
        monochrome = true,
        strict = true
)

public class TestRunner {
}
