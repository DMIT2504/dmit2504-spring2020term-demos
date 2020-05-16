package ca.nait.dmit2504.jitterapp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface YoucodeService {

    @GET("JSONServlet") // response body format: [date] from [login_name] *** [data]
    Call<String> listJSONServlet();

    @FormUrlEncoded
    @POST("JitterServlet")
    Call<String> postJitter(@Field("LOGIN_NAME") String loginName, @Field("DATA") String data);

}
