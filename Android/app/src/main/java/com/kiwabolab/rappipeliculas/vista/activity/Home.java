package com.kiwabolab.rappipeliculas.vista.activity;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.kiwabolab.rappipeliculas.R;
import com.kiwabolab.rappipeliculas.modelo.ListaPeliculas;
import com.kiwabolab.rappipeliculas.modelo.Pelicula;
import com.kiwabolab.rappipeliculas.network.Servidor;
import com.kiwabolab.rappipeliculas.presentacion.home.HomeContrato;
import com.kiwabolab.rappipeliculas.presentacion.home.HomePresenter;
import com.kiwabolab.rappipeliculas.vista.adapter.AdaptadorPelicula;
import com.kiwabolab.rappipeliculas.vista.adapter.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.mateware.snacky.Snacky;
import es.dmoral.toasty.Toasty;

import static com.kiwabolab.rappipeliculas.vista.activity.Detalle.EXTRA_PELICULA;

public class Home extends AppCompatActivity implements HomeContrato.VistaHome {
    //----------------------------------------------------------------------------------------------
    //Variables
    @BindView(R.id.mi_recicler)RecyclerView recyclerView;
    @BindView(R.id.searchView)SearchView searchView;
    @BindView(R.id.container)RelativeLayout view;
    @BindView(R.id.loadingx)RelativeLayout cargando;

    private HomePresenter presenter;
    private ListaPeliculas peliculasP;
    private ListaPeliculas peliculasT;
    private ListaPeliculas peliculasU;
    private List<Pelicula> peliculaList = new ArrayList<>();
    private List<Pelicula> filtrada = new ArrayList<>();

    private int lista = 0;
    private AdaptadorPelicula adaptadorPelicula;
    private Context context;
    private Servidor servidor;
    private boolean fileter = false;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if(!servidor.isOnline()){
                Toasty.warning(getApplicationContext(), "Revisa tu conexión a Internet", Toast.LENGTH_SHORT, true).show();

            }
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    peliculaList.clear();
                    adaptadorPelicula.notifyDataSetChanged();
                    lista = 1;
                    getPeliculas("popular",1, context);
                    searchView.setEnabled(true);
                    return true;
                case R.id.navigation_dashboard:
                    peliculaList.clear();
                    adaptadorPelicula.notifyDataSetChanged();
                    lista = 2;
                    getPeliculas("top_rated",1, context);
                    searchView.setEnabled(true);
                    return true;
                case R.id.navigation_notifications:
                    peliculaList.clear();
                    adaptadorPelicula.notifyDataSetChanged();
                    lista = 3;
                    getPeliculas("upcoming",1, context);
                    searchView.setEnabled(true);
                    return true;
                case R.id.navigation_search:
                    peliculaList.clear();
                    adaptadorPelicula.notifyDataSetChanged();
                    lista = 4;
                    if(servidor.isOnline()){
                        searchView.setEnabled(true);
                    }else{
                        searchView.setEnabled(false);
                        Toasty.warning(getApplicationContext(), "Debes estar conectado para el buscador online", Toast.LENGTH_SHORT, true).show();
                    }
                    return true;
            }
            return false;
        }
    };

    //----------------------------------------------------------------------------------------------
    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        this.presenter = new HomePresenter(this);
        peliculasT = new ListaPeliculas();
        peliculasP = new ListaPeliculas();
        peliculasU = new ListaPeliculas();

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        this.context = this;
        initVista();
    }
    //----------------------------------------------------------------------------------------------
    //
    private void initVista(){
        servidor = new Servidor(this);
        switch (lista){
            case 1:
                adaptadorPelicula = new AdaptadorPelicula(peliculasP.peliculas, context);
                break;
            case 2:
                adaptadorPelicula = new AdaptadorPelicula(peliculasT.peliculas, context);
                break;
            case 3:
                adaptadorPelicula = new AdaptadorPelicula(peliculasU.peliculas, context);
                break;
        }

        adaptadorPelicula = new AdaptadorPelicula(peliculaList, context);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorPelicula);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Pelicula movie;
                if(fileter){
                    movie = filtrada.get(position);
                }else{
                    movie = peliculaList.get(position);
                }
                Intent intent = new Intent(context, Detalle.class);
                intent.putExtra(EXTRA_PELICULA,movie);
                context.startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                Log.v("NOMBRE",peliculaList.get(position).title);
            }
        }));

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        lista = 1;
        if(servidor.isOnline()){
            getPeliculas("popular",1,context);
        }else{
            getPeliculas("popular",1,context);
            String msg = getResources().getString(R.string.warningConexion);
            msgWarning(msg);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if(lista == 4){
                    if (newText.length() > 0) {
                        filtroLista(newText);
                        fileter = true;
                        getFiltradas(newText, context);
                    } else {
                        peliculaList.clear();
                        adaptadorPelicula = new AdaptadorPelicula(peliculaList, context);
                        adaptadorPelicula.notifyDataSetChanged();
                        recyclerView.setAdapter(adaptadorPelicula);
                        fileter = false;
                    }
                }else{
                    if (newText.length() > 0) {
                        filtroLista(newText);
                        fileter = true;
                    } else {
                        adaptadorPelicula = new AdaptadorPelicula(peliculaList, context);
                        adaptadorPelicula.notifyDataSetChanged();
                        recyclerView.setAdapter(adaptadorPelicula);
                        fileter = false;
                    }
                }
                return false;
            }
        });
    }
    //----------------------------------------------------------------------------------------------
    //
    private void filtroLista(String txt){
        filtrada.clear();
        for (Pelicula peli: peliculaList){
            if(peli.title.toLowerCase().contains(txt.toLowerCase())){
                filtrada.add(peli);
            }
        }

        adaptadorPelicula = new AdaptadorPelicula(filtrada, context);
        adaptadorPelicula.notifyDataSetChanged();
        recyclerView.setAdapter(adaptadorPelicula);
    }
    //----------------------------------------------------------------------------------------------
    //
    private void msgError(String msg){
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
    private void msgSuccess(String msg){
        Snacky.builder()
                .setView(view)
                .setText(msg)
                .setDuration(Snacky.LENGTH_INDEFINITE)
                .setActionText(android.R.string.ok)
                .success()
                .show();
    }
    //----------------------------------------------------------------------------------------------
    //
    private void msgWarning(String msg){
        Snacky.builder()
                .setView(view)
                .setText(msg)
                .setDuration(Snacky.LENGTH_LONG)
                .setActionText(android.R.string.ok)
                .warning()
                .show();
    }
    //----------------------------------------------------------------------------------------------
    //
    @Override
    public void showLoading() {
        cargando.setVisibility(View.VISIBLE);
    }
    @Override
    public void closeLoading() {
        cargando.setVisibility(View.GONE);
    }

    @Override
    public void getPeliculas(String categoria, int page, Context context) {
        presenter.getPeliculas(categoria, page, context);
    }

    @Override
    public void getPeliculasOk(ListaPeliculas peliculas) {
        Log.v("MENSAJE","LLEGAN LAS LISTAS "+peliculas.peliculas.size());
        switch (lista){
            case 1:
                this.peliculasT = peliculas;

                break;
            case 2:
                this.peliculasP = peliculas;
                break;
            case 3:
                this.peliculasU = peliculas;
                break;
        }
        for(Pelicula m:peliculas.peliculas){
            peliculaList.add(m);
        }
        adaptadorPelicula.notifyDataSetChanged();
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
        presenter.getFiltradas(nombre, context);
    }

    @Override
    public void getFiltradasOk(ListaPeliculas peliculas) {
        filtrada.clear();
        filtrada = peliculas.peliculas;
        adaptadorPelicula = new AdaptadorPelicula(filtrada, context);
        adaptadorPelicula.notifyDataSetChanged();
        recyclerView.setAdapter(adaptadorPelicula);
    }

    @Override
    public void getFiltradasError() {

    }

    @Override
    public void getFiltradasProblema() {

    }
}