package runnerFile;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/featureFiles",
		glue = "stepDefinitions",
		monochrome = true,
		//uncomment the below tags line to run a specific scenario
		tags = "@webAuto",
		plugin = {"pretty",
                "html:target/cucumber.html",
                "json:target/cucumber.json"}
		)


public class TestRunner {


}