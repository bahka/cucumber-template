package org.bahka.cucumbertemplate.pages.service;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;

public class SomePage {

    private static final SelenideElement fieldInput = $("input");

    public static void setValueToField(String value) {
        fieldInput.shouldBe(enabled).setValue(value);
    }

}
