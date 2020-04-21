package testrunners;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="./tests/rawfeatures/", glue="stepdefinitions", plugin = {"pretty", "json:target/cuke-results.json"})
public class TestRunner {
    
    
}
