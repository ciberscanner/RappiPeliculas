package com.kiwabolab.rappipeliculas.network.retrofit;

import com.kiwabolab.rappipeliculas.modelo.ListaGeneros;
import com.kiwabolab.rappipeliculas.modelo.ListaPeliculas;
import com.kiwabolab.rappipeliculas.modelo.Token;


import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.kiwabolab.rappipeliculas.BuildConfig.KEYAPI;
import static com.kiwabolab.rappipeliculas.BuildConfig.LANG;

public interface RestClient {
    //----------------------------------------------------------------------------------------------
    //request_token
    @FormUrlEncoded
    @POST("auth/request_token")
    Call<Token> requestToken();
    //----------------------------------------------------------------------------------------------
    //Obtener lista de generos
    @GET("genre/movie/list?api_key="+KEYAPI+LANG)
    Call<ListaGeneros> getGeneros();
    //----------------------------------------------------------------------------------------------
    //obtener lista de peliculas
    @GET("movie/{val}")
    Call<ListaPeliculas> getPeliculas(@Path("val") String taskId, @Query("api_key") String key, @Query("language") String lang, @Query("page") int page);
    //----------------------------------------------------------------------------------------------
    //
}