
package com.kiwabolab.rappipeliculas.modelo;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pelicula implements Serializable
{

    @SerializedName("popularity")
    @Expose
    public Float popularity;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("video")
    @Expose
    public Boolean video;
    @SerializedName("vote_count")
    @Expose
    public Integer voteCount;
    @SerializedName("vote_average")
    @Expose
    public Float voteAverage;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("release_date")
    @Expose
    public String releaseDate;
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("original_title")
    @Expose
    public String originalTitle;
    @SerializedName("genre_ids")
    @Expose
    public List<Integer> genreIds = null;
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;
    @SerializedName("adult")
    @Expose
    public Boolean adult;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    private final static long serialVersionUID = 978379554562985322L;

}
