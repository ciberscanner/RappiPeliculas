package com.kiwabolab.rappipeliculas.network.retrofit;

import com.kiwabolab.rappipeliculas.modelo.PeliculaFull;
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
    //obtener lista de peliculas
    @GET("movie/{val}")
    Call<ListaPeliculas> getPeliculas(@Path("val") String taskId, @Query("api_key") String key, @Query("language") String lang, @Query("page") int page);
    //----------------------------------------------------------------------------------------------
    //obtener la informacion completa de una pelicula
    @GET("movie/{val}?api_key="+KEYAPI+"&append_to_response=videos"+LANG)
    Call<PeliculaFull> getMovieInfo(@Path("val") String taskId);
    //----------------------------------------------------------------------------------------------
    //Busqueda online
    @GET("search/movie")
    Call<ListaPeliculas> getPeliculasFiltradas(@Query("api_key") String key, @Query("language") String lang, @Query("query") String val, @Query("page") int page);
}