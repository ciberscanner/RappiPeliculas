package com.kiwabolab.rappipeliculas.presentacion.detalle;

import android.content.Context;
import android.util.Log;

import com.kiwabolab.rappipeliculas.modelo.PeliculaFull;
import com.kiwabolab.rappipeliculas.network.retrofit.RestApiAdapter;
import com.kiwabolab.rappipeliculas.presentacion.home.HomeInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemInteractor implements ItemContrato.InteractorItem {
    //----------------------------------------------------------------------------------------------
    //Variables
    private final ItemContrato.PresenterItem presenterItem;
    //----------------------------------------------------------------------------------------------
    //constructor
    public ItemInteractor(ItemContrato.PresenterItem presenterItem) {
        this.presenterItem = presenterItem;
    }
    //----------------------------------------------------------------------------------------------
    //Obtener la informacion de una pelicula por id, para obtener los videos
    @Override
    public void getInfoMovie(String id, Context context) {
        Call<PeliculaFull> call = new RestApiAdapter(context).EstablecerConexion(3).getMovieInfo(id);
        call.enqueue(new Callback<PeliculaFull>() {
            @Override
            public void onResponse(Call<PeliculaFull> call, Response<PeliculaFull> response) {
                Log.d(HomeInteractor.class.getSimpleName(),
                        String.format("--dfg %s. Infopelicula: %s", HomeInteractor.class.getSimpleName(), "getMovieInfo", response.body()));
                if (response.isSuccessful()) {
                    presenterItem.getInfoMovieOk(response.body());
                } else {
                    presenterItem.getInfoMovieError();
                }
            }
            @Override
            public void onFailure(Call<PeliculaFull> call, Throwable t) {
                presenterItem.getInfoMovieError();
            }
        });
    }
}
