package com.kiwabolab.rappipeliculas.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Servidor {
    //----------------------------------------------------------------------------------------------
    //Variables
    public String API3 = "https://api.themoviedb.org/3/";
    public String API4 = "https://api.themoviedb.org/4/";
    public String Image_Url = "https://image.tmdb.org/t/p/w500/";
    //https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg

    private Context context;
    //----------------------------------------------------------------------------------------------
    //Contructor
    public Servidor() {
    }
    //----------------------------------------------------------------------------------------------
    //Constructor 2
    public Servidor(Context context) {
        this.context = context;
    }
    //----------------------------------------------------------------------------------------------
    //
    public String getImg(String img){
        return Image_Url + img;
    }
    //----------------------------------------------------------------------------------------------
    /** Metodo que comprube si hay conexion a internet*/
    public boolean isOnline() {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }

        /*

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;*/
    }
}
