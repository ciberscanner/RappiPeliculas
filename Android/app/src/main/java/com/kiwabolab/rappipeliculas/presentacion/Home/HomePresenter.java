package com.kiwabolab.rappipeliculas.presentacion.Home;

import com.kiwabolab.rappipeliculas.modelo.ListaGeneros;
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

    @Override
    public void getGeneros() {
        interactor.getGeneros();
    }

    @Override
    public void getGenerosOk(ListaGeneros generos) {

    }

    @Override
    public void getGenerosError() {

    }

    @Override
    public void getGenerosProblema() {

    }

    @Override
    public void getPeliculas(String categoria, int page) {
        interactor.getPeliculas(categoria, page);
    }

    @Override
    public void getPeliculasOk(ListaPeliculas peliculas) {

    }

    @Override
    public void getPeliculasError() {

    }

    @Override
    public void getPeliculasProblema() {

    }
}
