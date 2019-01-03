
package com.kiwabolab.rappipeliculas.modelo;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListaGeneros implements Parcelable
{

    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;
    public final static Creator<ListaGeneros> CREATOR = new Creator<ListaGeneros>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ListaGeneros createFromParcel(Parcel in) {
            return new ListaGeneros(in);
        }

        public ListaGeneros[] newArray(int size) {
            return (new ListaGeneros[size]);
        }

    }
    ;

    protected ListaGeneros(Parcel in) {
        in.readList(this.genres, (Genre.class.getClassLoader()));
    }

    public ListaGeneros() {
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(genres);
    }

    public int describeContents() {
        return  0;
    }

}
