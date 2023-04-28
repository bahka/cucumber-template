package org.bahka.cucumbertemplate.api.backend.models;

import org.bahka.cucumbertemplate.keyvalue.SecretsStorage;

public class AuthnRequest {
    public final String username = SecretsStorage.value;
    public final String password = SecretsStorage.value;
    public final Options options = new Options();

    public static class Options {
        public final Boolean warnBeforePasswordExpired = true;
        public final Boolean multiOptionalFactorEnroll = false;
    }
}
