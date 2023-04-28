package org.bahka.cucumbertemplate.api.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthnResponse {
    public String sessionToken;
}
