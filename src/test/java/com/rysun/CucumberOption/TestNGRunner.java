package com.rysun.CucumberOption;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Features",

		glue = "StepDefination", monochrome = true, tags = "@TestAddUser",

		plugin = { "pretty", "html:target/cucumberReport.html" })
public class TestNGRunner extends AbstractTestNGCucumberTests {
}
