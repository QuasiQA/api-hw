package apitest.application.webservices;

import apitest.application.webservices.responses.DetectGetResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DetectEndpoint {

    /**
     * null values are ignored if passed to @Query parameter
     * So no get param will be added to request for null value
     * */
    @GET("detect")
    Call<DetectGetResponse> get(
            @Query("access_key") String accessKey,
            @Query("query") String text,
            @Query("show_query") String showQuery,
            @Query("callback") String callback,
            @Query("format") String format);
}
