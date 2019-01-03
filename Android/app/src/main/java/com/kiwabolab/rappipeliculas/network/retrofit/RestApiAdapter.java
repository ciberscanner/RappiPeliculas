package com.kiwabolab.rappipeliculas.network.retrofit;

import com.kiwabolab.rappipeliculas.BuildConfig;
import com.kiwabolab.rappipeliculas.network.Servidor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestApiAdapter {
    //----------------------------------------------------------------------------------------------
    //Variables
    Servidor servidor;
    //----------------------------------------------------------------------------------------------
    //Constructor
    public RestApiAdapter() {
        this.servidor = new Servidor();
    }
    //----------------------------------------------------------------------------------------------
    /**Recibe la url base del servidor*/
    public RestClient EstablecerConexion(int api){
        OkHttpClient.Builder okHttpbuilder = new OkHttpClient().newBuilder();
        okHttpbuilder.addInterceptor(new AppInterceptor());
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpbuilder.addInterceptor(loggingInterceptor);
        }
        okHttpbuilder.retryOnConnectionFailure(false);
        okHttpbuilder.readTimeout(48, TimeUnit.SECONDS);
        okHttpbuilder.writeTimeout(60, TimeUnit.SECONDS);
        OkHttpClient client = okHttpbuilder.build();

        if(api == 3){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(servidor.API3)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            return retrofit.create(RestClient.class);
        }else{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(servidor.API4)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            return retrofit.create(RestClient.class);
        }
    }
}