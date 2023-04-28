package org.bahka.cucumbertemplate.steps;

import io.cucumber.java.en.Then;
import lombok.SneakyThrows;
import org.cucumber.example.utils.TestDataStorage;
import org.bahka.cucumbertemplate.testcontainers.UpdGenContainer;

import java.util.Arrays;

public class UdpGenSteps {

    @SneakyThrows
    @Then("send {int} packets of {string} traffic to {int} port")
    public void generateNetflow9(Integer number, String flowType, Integer port) {
        if (!Arrays.asList("netflow9", "netflow5", "ipfix").contains(flowType)) {
            throw new RuntimeException("Do not support this type");
        }

        try (
                UpdGenContainer updGenContainer = new UpdGenContainer(
                        TestDataStorage.getUdpPort(),
                        flowType,
                        number)
        ) {
            updGenContainer.start();
        }
    }
}
