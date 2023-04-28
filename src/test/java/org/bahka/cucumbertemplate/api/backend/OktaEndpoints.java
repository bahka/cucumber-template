package org.bahka.cucumbertemplate.api.backend;

import okhttp3.ResponseBody;
import org.bahka.cucumbertemplate.api.backend.models.AuthnRequest;
import org.bahka.cucumbertemplate.api.backend.models.AuthnResponse;
import org.bahka.cucumbertemplate.api.backend.models.TokenResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OktaEndpoints {
    @POST("api/v1/authn")
    default Call<AuthnResponse> authn(@Body AuthnRequest body) {
        return null;
    }

    @GET("oauth2/{companyId}/v1/authorize")
    Call<ResponseBody> authorize(
            @Path("companyId") String companyId,
            @Query("client_id") String clientId,
            @Query("code_challenge") String codeChallenge,
            @Query("code_challenge_method") String codeChallengeMethod,
            @Query("nonce") String nonce,
            @Query("prompt") String prompt,
            @Query("scope") String scope,
            @Query("redirect_uri") String redirectUri,
            @Query("sessionToken") String sessionToken,
            @Query("state") String state,
            @Query("response_mode") String responseMode,
            @Query("response_type") String responseType
    );

    @FormUrlEncoded
    @POST("oauth2/{companyId}/v1/token")
    Call<TokenResponse> token(
            @Path("companyId") String companyId,
            @Field("client_id") String clientId,
            @Field("redirect_uri") String redirectUri,
            @Field("grant_type") String grantType,
            @Field("code_verifier") String codeVerifier,
            @Field("code") String code
    );
}
