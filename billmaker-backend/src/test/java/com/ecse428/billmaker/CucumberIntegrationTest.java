package com.ecse428.billmaker;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
        tags = "not @Sprint1 and not @Sprint3")
public class CucumberIntegrationTest {
}
