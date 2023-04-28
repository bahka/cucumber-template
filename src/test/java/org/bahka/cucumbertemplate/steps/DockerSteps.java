package org.bahka.cucumbertemplate.steps;

import io.cucumber.java.en.When;
import org.bahka.cucumbertemplate.testcontainers.UdpPortsContainer;

public class DockerSteps {

    @When("Run upd container")
    public void runContainer() {
        new UdpPortsContainer();
    }
}
