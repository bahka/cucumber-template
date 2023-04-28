package org.bahka.cucumbertemplate;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class CucumberHooks {
    @Before("@tag")
    public static void setUp() {
        // setup once before all scenarios with @tag
        if (Selenide.webdriver().driver().hasWebDriverStarted()) {
            return;
        }
    }

    @After("@tag")
    public static void tearDownCloud() {
        // triggered for all features/scenarios with @tag
    }

    @BeforeAll
    public static void tearUp() {
        // before all tests
    }

    @AfterAll
    public static void tearDown() {
        // after all tests
    }
}
