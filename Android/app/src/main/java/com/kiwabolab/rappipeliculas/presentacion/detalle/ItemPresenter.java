package com.kiwabolab.rappipeliculas.presentacion.detalle;

import android.content.Context;

import com.kiwabolab.rappipeliculas.modelo.PeliculaFull;


public class ItemPresenter implements ItemContrato.PresenterItem{
    //----------------------------------------------------------------------------------------------
    //Variables
    ItemContrato.VistaItem itemView;
    ItemContrato.InteractorItem itemInteractor;
    //----------------------------------------------------------------------------------------------
    //constructor
    public ItemPresenter(ItemContrato.VistaItem itemView) {
        this.itemView = itemView;
        itemInteractor = new ItemInteractor(this);
    }
    //----------------------------------------------------------------------------------------------
    //
    @Override
    public void getInfoMovie(String id, Context context) {
        itemInteractor.getInfoMovie(id, context);
    }
    //----------------------------------------------------------------------------------------------
    //
    @Override
    public void getInfoMovieOk(PeliculaFull im) {
        itemView.setInfoMovie(im);
    }
    //----------------------------------------------------------------------------------------------
    //
    @Override
    public void getInfoMovieError() {
        itemView.mostrarErrorInfoMovie();
    }
}