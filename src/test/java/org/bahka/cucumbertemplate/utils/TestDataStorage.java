package org.bahka.cucumbertemplate.utils;

import org.bahka.cucumbertemplate.keyvalue.SecretsStorage;

public class TestDataStorage {
    public static String mapUserToEmail(String email) {
        return switch (email) {
            case "ADMIN" -> SecretsStorage.value;
            case "MEMBER" -> SecretsStorage.value;
            case "EXTRA" -> SecretsStorage.value;
            default -> email;
        };
    }


    public static int getUdpPort() {
        return 1;
    }
}
