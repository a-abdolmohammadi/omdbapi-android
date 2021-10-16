package org.yara.movies.data.remote.api;

import org.yara.movies.data.remote.model.response.details.Details;
import org.yara.movies.data.remote.model.response.search.SearchList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    String movie_API_BASE_URL = "http://www.omdbapi.com/";

    @GET(".")
    Observable<SearchList> getMovie(@Query("s") String search);

    @GET(".")
    Observable<Details> getDetails(@Query("i") String imdbID);

}


