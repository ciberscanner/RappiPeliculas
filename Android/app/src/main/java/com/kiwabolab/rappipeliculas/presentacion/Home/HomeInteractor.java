package com.kiwabolab.rappipeliculas.presentacion.Home;

import android.util.Log;

import com.kiwabolab.rappipeliculas.modelo.ListaGeneros;
import com.kiwabolab.rappipeliculas.network.retrofit.RestApiAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void getPeliculas() {

    }
}
