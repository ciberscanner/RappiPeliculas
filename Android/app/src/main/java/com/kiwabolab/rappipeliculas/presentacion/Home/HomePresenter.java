package com.kiwabolab.rappipeliculas.presentacion.home;

import android.content.Context;

import com.kiwabolab.rappipeliculas.modelo.ListaPeliculas;

public class HomePresenter implements HomeContrato.PresentadorHome {
    //----------------------------------------------------------------------------------------------
    //Variables
    HomeContrato.VistaHome vista;
    HomeContrato.InteractorHome interactor;
    //----------------------------------------------------------------------------------------------
    //Constructor
    public HomePresenter(HomeContrato.VistaHome vista) {
        this.vista = vista;
        this.interactor = new HomeInteractor(this);
    }
    //----------------------------------------------------------------------------------------------
    //
    @Override
    public void showLoading() {
        vista.showLoading();
    }

    @Override
    public void closeLoading() {
        vista.closeLoading();
    }
    //----------------------------------------------------------------------------------------------
    //
    @Override
    public void getPeliculas(String categoria, int page, Context context) {
        showLoading();
        interactor.getPeliculas(categoria, page, context);
    }

    @Override
    public void getPeliculasOk(ListaPeliculas peliculas) {
        vista.getPeliculasOk(peliculas);
        closeLoading();
    }

    @Override
    public void getPeliculasError() {

    }

    @Override
    public void getPeliculasProblema() {

    }
    //----------------------------------------------------------------------------------------------
    //
    @Override
    public void getFiltradas(String nombre, Context context) {
        interactor.getFiltradas(nombre,context);
    }

    @Override
    public void getFiltradasOk(ListaPeliculas peliculas) {
        vista.getFiltradasOk(peliculas);
    }

    @Override
    public void getFiltradasError() {

    }

    @Override
    public void getFiltradasProblema() {

    }
}
