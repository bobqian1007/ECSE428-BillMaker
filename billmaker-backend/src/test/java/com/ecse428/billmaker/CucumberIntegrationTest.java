package com.ecse428.billmaker;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
        tags = "not @Sprint3 and not @Sprint1")
public class CucumberIntegrationTest {
}
