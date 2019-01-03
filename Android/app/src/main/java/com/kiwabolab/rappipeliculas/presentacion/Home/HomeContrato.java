package com.kiwabolab.rappipeliculas.presentacion.Home;

import com.kiwabolab.rappipeliculas.modelo.ListaGeneros;
import com.kiwabolab.rappipeliculas.modelo.ListaPeliculas;

public interface HomeContrato {
    //----------------------------------------------------------------------------------------------
    //Vista
    interface VistaHome{
        void showLoading();
        void closeLoading();

        void getGeneros();
        void getGenerosOk(ListaGeneros generos);
        void getGenerosError();
        void getGenerosProblema();

        void getPeliculas();
        void getPeliculasOk(ListaPeliculas peliculas);
        void getPeliculasError();
        void getPeliculasProblema();
    }
    //----------------------------------------------------------------------------------------------
    //Presentador
    interface PresentadorHome{
        void showLoading();
        void closeLoading();

        void getGeneros();
        void getGenerosOk(ListaGeneros generos);
        void getGenerosError();
        void getGenerosProblema();

        void getPeliculas();
        void getPeliculasOk(ListaPeliculas peliculas);
        void getPeliculasError();
        void getPeliculasProblema();
    }
    //----------------------------------------------------------------------------------------------
    //Interactor
    interface InteractorHome{
        void getGeneros();
        void getPeliculas();
    }
}