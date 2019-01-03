
package com.kiwabolab.rappipeliculas.modelo;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListaPeliculas implements Parcelable
{

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("peliculas")
    @Expose
    private List<Pelicula> peliculas = null;
    public final static Creator<ListaPeliculas> CREATOR = new Creator<ListaPeliculas>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ListaPeliculas createFromParcel(Parcel in) {
            return new ListaPeliculas(in);
        }

        public ListaPeliculas[] newArray(int size) {
            return (new ListaPeliculas[size]);
        }

    }
    ;

    protected ListaPeliculas(Parcel in) {
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.peliculas, (Pelicula.class.getClassLoader()));
    }

    public ListaPeliculas() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(totalResults);
        dest.writeValue(totalPages);
        dest.writeList(peliculas);
    }

    public int describeContents() {
        return  0;
    }

}
