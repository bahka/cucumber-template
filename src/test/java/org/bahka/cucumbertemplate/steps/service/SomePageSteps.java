package org.bahka.cucumbertemplate.steps.service;

import io.cucumber.java.en.Then;
import org.bahka.cucumbertemplate.pages.service.SomePage;

public class SomePageSteps {

    @Then("set {string} to 'name' field")
    public static void setValueToField(String value) {
        SomePage.setValueToField(value);
    }
}
