package base.climateapp.root.root.api;

import java.util.List;

import base.climateapp.root.root.model.entity.Account;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InterfaceAPI {
    //THOSE ARE EXAMPLES
    @GET("apiuser/{username}/{password}")
    Call<Account> logIn(
            @Path(value = "username", encoded = true) String username,
            @Path(value = "password", encoded = true) String password
    );
    @PUT("APISaunas/{id}")
    Call<Void> setThresholds(
            @Path(value = "id", encoded = true) int id,
            @Body int sauna
    );

    @GET("APINotificationHistories")
    Call<List<Integer>>  checkNotification();

    @GET("hardwarecontroller/getopenthedoor/{id}")
    Call<Void> openDoor(
            @Path(value = "id", encoded = true) int id
    );
}
