package org.bahka.cucumbertemplate.keyvalue;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;

public class SecretsStorage {
    /**
     * To work with Azure Key vault you need to set ENV vars for Service Principal, the same for CI where you run tests
     * - AZURE_CLIENT_ID,
     * - AZURE_TENANT_ID,
     * - AZURE_CLIENT_SECRET
     * It's static to avoid network issues when we requests the same values from KV
     */

    public static final SecretClient secretClient = new SecretClientBuilder()
            .vaultUrl("https://{name}.vault.azure.net/")
            .credential(new DefaultAzureCredentialBuilder().build())
            .buildClient();

    public static String envRelated = getSecretValueForEnv("value-for-specific-env");
    public static String value = getSecretValue("value");


    private static String getSecretValueForEnv(String secret) {
        String env = System.getProperty("ENV");
        return secretClient.getSecret(String.format("%s-%s", secret, env)).getValue();
    }

    private static String getSecretValue(String secret) {
        return secretClient.getSecret(secret).getValue();
    }
}
