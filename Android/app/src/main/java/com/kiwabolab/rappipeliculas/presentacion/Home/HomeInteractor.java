package com.kiwabolab.rappipeliculas.presentacion.Home;

import android.util.Log;

import com.kiwabolab.rappipeliculas.modelo.ListaGeneros;
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
    //getGeneros
    @Override
    public void getGeneros() {
        Call<ListaGeneros> call = new RestApiAdapter().EstablecerConexion(3).getGeneros();
        call.enqueue(new Callback<ListaGeneros>() {
            @Override
            public void onResponse(Call<ListaGeneros> call, Response<ListaGeneros> response) {
                Log.d(HomeInteractor.class.getSimpleName(),
                        String.format("--dfg %s. genero: %s", HomeInteractor.class.getSimpleName(), "getGeneros", response.body()));
                if (response.isSuccessful()) {
                    presentador.getGenerosOk(response.body());
                } else {
                    presentador.getGenerosProblema();
                }
            }
            @Override
            public void onFailure(Call<ListaGeneros> call, Throwable t) {
                presentador.getGenerosError();
            }
        });
    }
    //----------------------------------------------------------------------------------------------
    //getPeliculas
    @Override
    public void getPeliculas(String categoria, int page) {
        Call<ListaPeliculas> call = new RestApiAdapter().EstablecerConexion(3).getPeliculas(categoria, KEYAPI,"es-ES", page);
        call.enqueue(new Callback<ListaPeliculas>() {
            @Override
            public void onResponse(Call<ListaPeliculas> call, Response<ListaPeliculas> response) {
                Log.d(HomeInteractor.class.getSimpleName(),
                        String.format("--dfg %s. genero: %s", HomeInteractor.class.getSimpleName(), "getGeneros", response.body()));
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
}
