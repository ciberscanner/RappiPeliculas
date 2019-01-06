package com.kiwabolab.rappipeliculas.presentacion.detalle;

import android.content.Context;

import com.kiwabolab.rappipeliculas.modelo.PeliculaFull;


public interface ItemContrato {
    //----------------------------------------------------------------------------------------------
    //
    interface VistaItem{
        void getInfoMovie(String id, Context context);
        void mostrarErrorInfoMovie();
        void setInfoMovie(PeliculaFull info);
    }
    //----------------------------------------------------------------------------------------------
    //
    interface InteractorItem{
        void getInfoMovie(String id, Context context);
    }
    //----------------------------------------------------------------------------------------------
    //
    interface PresenterItem{
        void getInfoMovie(String id, Context context);
        void getInfoMovieOk(PeliculaFull im);
        void getInfoMovieError();
    }
}