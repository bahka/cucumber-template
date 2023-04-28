package org.bahka.cucumbertemplate.api.backend;

import org.bahka.cucumbertemplate.api.backend.models.GetEntityResponse;
import org.bahka.cucumbertemplate.api.backend.models.GetInstanceUsersResponse;
import org.bahka.cucumbertemplate.api.backend.models.PostEntityRequest;
import org.bahka.cucumbertemplate.api.backend.models.PostEntityResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BackendEndpoints {

    @POST("api/{organization}/entities")
    Call<PostEntityResponse> createEntity(
            @Path("organization") String organization,
            @Body PostEntityRequest body,
            @Header("authorization") String authToken
    );

    @GET("apy/{organization}/entities")
    Call<GetEntityResponse> getEntities(
            @Path("organization") String organization,
            @Query("limit") Integer limit,
            @Header("authorization") String authToken
    );

    @GET("api/v1/portal/{organization}/bto-instance/{instanceId}/users")
    Call<GetInstanceUsersResponse> getInstanceUsers(
            @Path("organization") String organization,
            @Path("instanceId") String instanceId,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset,
            @Header("authorization") String authToken
    );

    @POST("api/v1/portal/{organization}/bto-instance/{instanceId}/users")
    Call<Void> postInstanceUser(
            @Path("organization") String organization,
            @Path("instanceId") String instanceId,
            @Body PostEntityResponse body,
            @Header("authorization") String authToken
    );

    @DELETE("api/v1/portal/{organization}/bto-instance/{instanceId}/users/{userOktaId}")
    Call<Void> deleteInstanceUser(
            @Path("organization") String organization,
            @Path("instanceId") String instanceId,
            @Path("userOktaId") String userOktaId,
            @Header("authorization") String authToken
    );

    @DELETE("api/v1/portal/{organization}/bto-instance/{instanceId}")
    Call<Void> deleteInstance(
            @Path("organization") String organization,
            @Path("instanceId") String instanceId,
            @Header("authorization") String authToken
    );
}
