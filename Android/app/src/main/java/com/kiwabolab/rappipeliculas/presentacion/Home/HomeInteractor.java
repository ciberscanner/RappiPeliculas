package com.kiwabolab.rappipeliculas.presentacion.home;

import android.content.Context;
import android.util.Log;

import com.kiwabolab.rappipeliculas.modelo.ListaPeliculas;
import com.kiwabolab.rappipeliculas.network.retrofit.RestApiAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kiwabolab.rappipeliculas.BuildConfig.KEYAPI;

public class HomeInteractor implements HomeContrato.InteractorHome {
    //----------------------------------------------------------------------------------------------
    //Variables
    HomeContrato.PresentadorHome presentador;
    //----------------------------------------------------------------------------------------------
    //Constructor
    public HomeInteractor(HomeContrato.PresentadorHome presentador) {
        this.presentador = presentador;
    }
    //----------------------------------------------------------------------------------------------
    //getPeliculas
    @Override
    public void getPeliculas(final String categoria, int page, Context context) {
        Call<ListaPeliculas> call = new RestApiAdapter(context).EstablecerConexion(3).getPeliculas(categoria, KEYAPI,"es-ES", page);
        call.enqueue(new Callback<ListaPeliculas>() {
            @Override
            public void onResponse(Call<ListaPeliculas> call, Response<ListaPeliculas> response) {
                Log.d(HomeInteractor.class.getSimpleName(),
                        String.format("--dfg %s. peliculas: %s", HomeInteractor.class.getSimpleName(), "getPeliculas "+categoria, response.body()));
                if (response.isSuccessful()) {
                    presentador.getPeliculasOk(response.body());
                } else {
                    presentador.getPeliculasProblema();
                }
            }
            @Override
            public void onFailure(Call<ListaPeliculas> call, Throwable t) {
                presentador.getPeliculasError();
            }
        });
    }
    //----------------------------------------------------------------------------------------------
    //
    @Override
    public void getFiltradas(String nombre, Context context) {
        Call<ListaPeliculas> call = new RestApiAdapter(context).EstablecerConexion(3).getPeliculasFiltradas(KEYAPI, "es-ES", nombre, 1);
        call.enqueue(new Callback<ListaPeliculas>() {
            @Override
            public void onResponse(Call<ListaPeliculas> call, Response<ListaPeliculas> response) {
                Log.d(HomeInteractor.class.getSimpleName(),
                        String.format("--dfg %s. peliculas: %s", HomeInteractor.class.getSimpleName(), "getFiltradas", response.body()));
                if (response.isSuccessful()) {
                    presentador.getFiltradasOk(response.body());
                } else {
                    presentador.getFiltradasProblema();
                }
            }
            @Override
            public void onFailure(Call<ListaPeliculas> call, Throwable t) {
                presentador.getFiltradasError();
            }
        });
    }
}