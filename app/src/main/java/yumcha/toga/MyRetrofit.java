package yumcha.toga;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import yumcha.toga.pojo.ListingPojo;

public interface MyRetrofit {

    String BASE_URL = "https://2826536e-d00d-4575-b35e-5562354bf840.mock.pstmn.io";

    @GET("/get")
    Call<ResponseBody> getListing();

    class Factory {
        public static MyRetrofit create(){

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(MyRetrofit.class);
        }

    }

}
