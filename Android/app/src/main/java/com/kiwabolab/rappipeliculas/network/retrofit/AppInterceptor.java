package com.kiwabolab.rappipeliculas.network.retrofit;

import android.util.Log;

import com.kiwabolab.rappipeliculas.BuildConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AppInterceptor implements Interceptor {
    //----------------------------------------------------------------------------------------------
    //Constructor
    public AppInterceptor() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(5)
                .methodOffset(10)
                .tag("RappiPeliculas-AppInterceptor")
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                if (BuildConfig.DEBUG) {
                    return true;
                } else if (priority == Logger.ERROR || priority == Logger.INFO) {
                    return true;
                }
                return false;
            }
        });
    }
    //----------------------------------------------------------------------------------------------
    //
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.addHeader("Authorization", "Bearer {eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYjJjMjBmODMxM2QxZWVlYWMzYTZiNGNkY2E1YmZiNyIsInN1YiI6IjU4YWM4NzgxYzNhMzY4NDliMDAxNDE2YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.WY9nxHPs86Hkt4uQ2SImrOZPucxahys8XtzMG56kEW4}");
        builder.addHeader("Content-Type", "application/json;charset=utf-8");
        if(request.body() != null){
            Log.v("BODY",request.body().toString());
        }
        request = builder.build();
        Response response = chain.proceed(request);
        return response;
    }
}