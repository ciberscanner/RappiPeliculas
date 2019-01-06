package com.kiwabolab.rappipeliculas.vista.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kiwabolab.rappipeliculas.R;
import com.kiwabolab.rappipeliculas.modelo.Pelicula;
import com.kiwabolab.rappipeliculas.modelo.PeliculaFull;
import com.kiwabolab.rappipeliculas.network.Servidor;
import com.kiwabolab.rappipeliculas.presentacion.detalle.ItemContrato;
import com.kiwabolab.rappipeliculas.presentacion.detalle.ItemPresenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.mateware.snacky.Snacky;

public class Detalle extends AppCompatActivity implements ItemContrato.VistaItem {
    //----------------------------------------------------------------------------------------------
    //Variables
    public static final String EXTRA_PELICULA = "EXTRA_PELICULA";
    private Pelicula pelicula;
    private String idvideo="";
    private  Servidor servidor;
    @BindView(R.id.img_movie_image) ImageView banner;
    @BindView(R.id.img_movie_image2) ImageView completa;
    @BindView(R.id.fab_star)FloatingActionButton fab_star;

    @BindView(R.id.containeritem) LinearLayout view;
    @BindView(R.id.txt_movie_description)TextView descripcion;
    @BindView(R.id.txt_movie_title) CollapsingToolbarLayout title;
    @BindView(R.id.txtestrellas) TextView estrellas;
    @BindView(R.id.txt_movie_title2) TextView title2;
    @BindView(R.id.txtvotos) TextView votos;
    @BindView(R.id.txt_movie_date) TextView calendar;

    private ItemPresenter presenter;

    //----------------------------------------------------------------------------------------------
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        ButterKnife.bind(this);
        servidor = new Servidor();
        displayHomeAsUpEnabled();
        presenter = new ItemPresenter(this);
        getExtrasFromIntent();
    }
    //----------------------------------------------------------------------------------------------
    //Constructor
    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    //----------------------------------------------------------------------------------------------
    //
    private void getExtrasFromIntent() {
        pelicula = (Pelicula) getIntent().getSerializableExtra(EXTRA_PELICULA);
        getInfoMovie(pelicula.id+"", this);
        //title.setTitle(pelicula.title);
        title2.setText(pelicula.title);
        votos.setText(pelicula.voteCount+"");
        calendar.setText(pelicula.releaseDate);
        estrellas.setText(pelicula.voteAverage+"/10");
        descripcion.setText(pelicula.overview);
        Picasso.with(getApplicationContext()).load(servidor.getImg(pelicula.backdropPath)).into(banner);
        Picasso.with(getApplicationContext()).load(servidor.getImg(pelicula.posterPath)).into(completa);
    }
    //----------------------------------------------------------------------------------------------
    //
    @Override
    public void getInfoMovie(String id, Context context) {
        presenter.getInfoMovie(id,context);
    }
    //----------------------------------------------------------------------------------------------
    //
    @Override
    public void mostrarErrorInfoMovie() {

    }
    //----------------------------------------------------------------------------------------------
    //
    @Override
    public void setInfoMovie(PeliculaFull info) {
        if(info.getVideos().getResults().size()==0){
            showWarning("Esta pelicula no tiene videos");
        }else{
            idvideo = info.getVideos().getResults().get(0).getKey();
            /*if(!memoria.botonVideo()){
                tutorial();
            }*/
        }
    }
    //----------------------------------------------------------------------------------------------
    //
    public void ShowVideo(View view){
        if(idvideo.isEmpty()){
            showWarning("Estamos consultando los videos itenta en un momento");
        }else{
            Intent i = new Intent(this, Video.class);
            i.putExtra("video", idvideo);
            startActivity(i);
        }
    }
    //----------------------------------------------------------------------------------------------
    //
    private void showError(String msg){
        Snacky.builder()
                .setView(view)
                .setText(msg)
                .setDuration(Snacky.LENGTH_INDEFINITE)
                .setActionText(android.R.string.ok)
                .error()
                .show();
    }
    //----------------------------------------------------------------------------------------------
    //
    private void showWarning(String msg){
        Snacky.builder()
                .setView(view)
                .setText(msg)
                .setDuration(Snacky.LENGTH_INDEFINITE)
                .setActionText(android.R.string.ok)
                .warning()
                .show();
    }
}