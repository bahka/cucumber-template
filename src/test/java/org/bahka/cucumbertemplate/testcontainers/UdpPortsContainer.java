package org.bahka.cucumbertemplate.testcontainers;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.InternetProtocol;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.time.Duration;
import java.util.List;

/**
 * Just an example of UDP port exposing in easy way
 */
public class UdpPortsContainer extends GenericContainer<UdpPortsContainer> {

    public UdpPortsContainer() {
        super("docker_image/label");
        withExposedPorts(1)
                .waitingFor(
                        Wait.forLogMessage(".*Sending heartbeat*", 2)
                                .withStartupTimeout(Duration.ofMinutes(2))
                )
                .waitingFor(Wait.forListeningPort().withStartupTimeout(Duration.ofMinutes(5)));

        // expose UDP ports here
        this.getPortBindings().addAll(List.of(
                ExposedPort.udp(1).toString(),
                ExposedPort.udp(2).toString(),
                ExposedPort.udp(3).toString()
        ));
    }

    public String getUdpPortBinding(int portNumber) {
        return this.getContainerInfo().getNetworkSettings().getPorts().getBindings()
                .get(new ExposedPort(portNumber, InternetProtocol.UDP))[0].getHostPortSpec();
    }

}
