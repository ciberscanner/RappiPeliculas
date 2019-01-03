package com.kiwabolab.rappipeliculas.network.retrofit;

import com.kiwabolab.rappipeliculas.modelo.ListaGeneros;
import com.kiwabolab.rappipeliculas.modelo.ListaPeliculas;
import com.kiwabolab.rappipeliculas.modelo.Token;


import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestClient {
    //----------------------------------------------------------------------------------------------
    //Variables
    String keyAPI ="3b2c20f8313d1eeeac3a6b4cdca5bfb7";
    String LANG = "&language=es-ES";
    //----------------------------------------------------------------------------------------------
    //request_token
    @FormUrlEncoded
    @POST("auth/request_token")
    Call<Token> requestToken();
    //----------------------------------------------------------------------------------------------
    //obtener lista de peliculas
    @GET("movie/{val}?api_key="+keyAPI+LANG)
    Call<ListaPeliculas> getPeliculas(@Path("val") String taskId);
    //----------------------------------------------------------------------------------------------
    //Obtener lista de generos
    @GET("genre/movie/list?api_key="+keyAPI+LANG)
    Call<ListaGeneros> getGeneros();
    //----------------------------------------------------------------------------------------------
    //
}