package com.kiwabolab.rappipeliculas.vista.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kiwabolab.rappipeliculas.R;
import com.kiwabolab.rappipeliculas.modelo.Pelicula;
import com.kiwabolab.rappipeliculas.network.Servidor;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorPelicula extends RecyclerView.Adapter<AdaptadorPelicula.MyViewHolder> {
    //----------------------------------------------------------------------------------------------
    //Variables
    private List<Pelicula> PeliculasList;
    private Context ctx;
    private Servidor servidor;
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;

    //----------------------------------------------------------------------------------------------
    //
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txt_item_title);
            img = (ImageView) view.findViewById(R.id.img_item_image);
        }
    }

    //----------------------------------------------------------------------------------------------
    //
    public List<Pelicula> getMovies() {
        return PeliculasList;
    }
    //----------------------------------------------------------------------------------------------
    //
    public void setMovies(List<Pelicula> movieResults) {
        this.PeliculasList = movieResults;
    }
    //----------------------------------------------------------------------------------------------
    //Constructor
    public AdaptadorPelicula(List<Pelicula> PeliculasList, Context ctx) {
        this.PeliculasList = PeliculasList;
        this.ctx = ctx;
        this.servidor = new Servidor();
    }
    //----------------------------------------------------------------------------------------------
    //
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pelicula, parent, false);
        return new MyViewHolder(itemView);
    }
    //----------------------------------------------------------------------------------------------
    //
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pelicula Pelicula = PeliculasList.get(position);
        holder.title.setText(Pelicula.title);
        Picasso.with(ctx)
                .load(servidor.getImg(Pelicula.backdropPath))
                .placeholder(R.mipmap.no_picture)
                .error(R.mipmap.no_picture)
                .into(holder.img);
    }
    //----------------------------------------------------------------------------------------------
    //
    @Override
    public int getItemCount() {
        return PeliculasList.size();
    }
}