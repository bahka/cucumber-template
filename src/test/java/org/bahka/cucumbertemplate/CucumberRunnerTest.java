package org.bahka.cucumbertemplate;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty",
                "json:cucumber.reports/cucumber-report.json",
                "html:cucumber.reports/cucumber-report.html"},
        tags = "@tag"
)
public class CucumberRunnerTest {
}
