package org.bahka.cucumbertemplate.api.backend;

import okhttp3.ResponseBody;
import org.bahka.cucumbertemplate.api.ServiceGenerator;
import org.bahka.cucumbertemplate.api.backend.models.AuthnRequest;
import org.bahka.cucumbertemplate.api.backend.models.AuthnResponse;
import org.bahka.cucumbertemplate.api.backend.models.GetEntityResponse;
import org.bahka.cucumbertemplate.api.backend.models.PostEntityRequest;
import org.bahka.cucumbertemplate.api.backend.models.PostEntityResponse;
import org.bahka.cucumbertemplate.api.backend.models.TokenResponse;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class BackendApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(BackendApi.class);
    private final OktaEndpoints oktaService;
    private final BackendEndpoints backendService;
    private final String authToken;

    public BackendApi() {
        oktaService = ServiceGenerator.createService("your_okta_host", OktaEndpoints.class);
        backendService = ServiceGenerator.createService("your_service_host", BackendEndpoints.class);

        String sessionToken = authn();
        String code = authorize(sessionToken);
        authToken = getAuthToken(code);
    }

    public PostEntityResponse createEntity() {
        try {
            Response<PostEntityResponse> response = backendService.createEntity(
                    "organization",
                    new PostEntityRequest("value"),
                    authToken
            ).execute();

            if (response.isSuccessful()) {
                return response.body();
            }
            Assert.fail("[TEST] Create an entity request return error: " + response.errorBody().string());
        } catch (IOException ex) {
            Assert.fail("[TEST] Create an entity request failed: " + ex.getMessage());
        }
        return null;
    }

    public GetEntityResponse getAllEntity() {
        try {
            Response<GetEntityResponse> instances = backendService.getEntities(
                    "organization",
                    100,
                    authToken
            ).execute();

            if (!instances.isSuccessful()) {
                Assert.fail("[TEST] Get all entities request return error " + instances.errorBody().string());
            }
            return instances.body();
        } catch (IOException e) {
            Assert.fail("[TEST] Get all entities request failed: " + e.getLocalizedMessage());
        }
        return null;
    }

    public void deleteEntityNamed(String name) {
        try {
            Response<Void> deleteResponse = backendService.deleteInstance(
                    "organization",
                    name,
                    authToken
            ).execute();

            if (!deleteResponse.isSuccessful()) {
                Assert.fail("[TEST] Delete an entity request return error: " + deleteResponse.errorBody().string());
            }
        } catch (IOException e) {
            Assert.fail("[TEST] Delete an entity request failed: " + e.getLocalizedMessage());
        }
    }

    public void deleteAllEntities() {
        List<GetEntityResponse.SubEntity> items = getAllEntity().entityList;

        for (GetEntityResponse.SubEntity item : items) {
            deleteEntityNamed(item.field);
            LOGGER.info("[TEST] Entity {} deleted", item.field);
        }
    }

    private String authn() {
        Response<AuthnResponse> authn = null;
        try {
            authn = oktaService.authn(new AuthnRequest()).execute();
            if (!authn.isSuccessful()) {
                Assert.fail(authn.errorBody().string());
            }
        } catch (IOException e) {
            Assert.fail("[TEST] Request to Portal /authn failed: " + e.getMessage());
        }
        Assert.assertNotNull(authn);
        LOGGER.info("[TEST] Okta session_token: {}", authn.body().sessionToken);
        return authn.body().sessionToken;
    }

    private String authorize(String sessionToken) {
        Response<ResponseBody> authorize;
        try {
            authorize = oktaService.authorize(
                    "oktaCompanyId",
                    "oktaClientId",
                    "oktaCodeChallenge",
                    "oktaCodeChallengeMethod",
                    "oktaNonce",
                    "none",
                    "openid",
                    "portalHost",
                    sessionToken,
                    "oktaState",
                    "query",
                    "code"
            ).execute();

            // the code is located in the `location` header:
            // location: https://xxx.cloud.opennms.com?code={code}&state={state}

            String code = authorize.headers().get("location").split("code=")[1].split("&")[0];

            LOGGER.info("[TEST] OKTA code: {}", code);
            return code;
        } catch (IOException ex) {
            Assert.fail("[TEST] Cannot parse the response from /authorize: " + ex);
        }
        return null;
    }

    private String getAuthToken(String code) {
        try {
            Response<TokenResponse> response = oktaService.token(
                    "oktaCompanyId",
                    "oktaClientId",
                    "service_host",
                    "authorization_code",
                    "oktaCodeVerifier",
                    code
            ).execute();

            if (!response.isSuccessful()) {
                Assert.fail("[TEST] OKTA.token failed:" + response.errorBody().string());
            }

            LOGGER.info("[TEST] OKTA auth token: {}", response.body().accessToken);
            return "Bearer " + response.body().accessToken;
        } catch (IOException e) {
            Assert.fail("[TEST] Cannot reach the backend");
        }
        return null;
    }
}
