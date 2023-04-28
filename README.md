The simples possible architecture for Cucumber tests with:

- testcontainers
- selenide with Static PageObject pattern (instead of Selenium)
- Azure KeyVault integration for sensitive information

## Sensitive data

All sensitive data stored in Azure KeyVault storage : `name`.

You need setup Service Principle information as ENV variables to connect
to Azure KeyVault.

- AZURE_CLIENT_ID,
- AZURE_TENANT_ID,
- AZURE_CLIENT_SECRET

all values stored in [SecretsStorage](src/test/java/org/bahka/cucumbertemplate/keyvalue/SecretsStorage.java)

## UDP port exposing in testcontainers

an example is located
in [UdpPortsContainer](src/test/java/org/bahka/cucumbertemplate/testcontainers/UdpPortsContainer.java)

## UPD netflow traffic generator as docker container

you can find it in [UpdGenContainer](src/test/java/org/bahka/cucumbertemplate/testcontainers/UpdGenContainer.java)