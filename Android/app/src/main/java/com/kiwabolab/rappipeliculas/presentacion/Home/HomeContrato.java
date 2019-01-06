package com.kiwabolab.rappipeliculas.presentacion.home;

import android.content.Context;

import com.kiwabolab.rappipeliculas.modelo.ListaPeliculas;

public interface HomeContrato {
    //----------------------------------------------------------------------------------------------
    //Vista
    interface VistaHome{
        void showLoading();
        void closeLoading();

        void getPeliculas(String categoria, int page, Context context);
        void getPeliculasOk(ListaPeliculas peliculas);
        void getPeliculasError();
        void getPeliculasProblema();

        void getFiltradas(String nombre, Context context);
        void getFiltradasOk(ListaPeliculas peliculas);
        void getFiltradasError();
        void getFiltradasProblema();
    }
    //----------------------------------------------------------------------------------------------
    //Presentador
    interface PresentadorHome{
        void showLoading();
        void closeLoading();

        void getPeliculas(String categoria, int page, Context context);
        void getPeliculasOk(ListaPeliculas peliculas);
        void getPeliculasError();
        void getPeliculasProblema();

        void getFiltradas(String nombre, Context context);
        void getFiltradasOk(ListaPeliculas peliculas);
        void getFiltradasError();
        void getFiltradasProblema();
    }
    //----------------------------------------------------------------------------------------------
    //Interactor
    interface InteractorHome{
        void getPeliculas(String categoria, int page, Context context);
        void getFiltradas(String nombre, Context context);
    }
}