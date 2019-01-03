package com.kiwabolab.rappipeliculas.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kiwabolab.rappipeliculas.R;
import com.kiwabolab.rappipeliculas.modelo.ListaGeneros;
import com.kiwabolab.rappipeliculas.modelo.ListaPeliculas;
import com.kiwabolab.rappipeliculas.presentacion.Home.HomeContrato;
import com.kiwabolab.rappipeliculas.presentacion.Home.HomePresenter;

public class Home extends AppCompatActivity implements HomeContrato.VistaHome {
    //----------------------------------------------------------------------------------------------
    //Variables
    private HomePresenter presenter;
    //----------------------------------------------------------------------------------------------
    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.presenter = new HomePresenter(this);

        getGeneros();
        getPeliculas("top_rated",1);
    }
    //----------------------------------------------------------------------------------------------
    //
    @Override
    public void showLoading() {

    }

    @Override
    public void closeLoading() {

    }

    @Override
    public void getGeneros() {
        presenter.getGeneros();
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
        presenter.getPeliculas(categoria, page);
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
