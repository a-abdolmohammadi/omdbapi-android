package org.yara.movies.repository;


import android.annotation.SuppressLint;

import org.yara.movies.data.local.dao.MovieDao;
import org.yara.movies.data.local.entity.Movie;
import org.yara.movies.data.remote.api.ApiServices;
import org.yara.movies.data.remote.model.response.details.Details;
import org.yara.movies.data.remote.model.response.search.Search;
import org.yara.movies.data.remote.model.response.search.SearchList;
import org.yara.movies.util.BatmanExecutors;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class MainRepository {

    private final ApiServices apiServices;
    private final MovieDao movieDao;
    private final BatmanExecutors executors;

    @Inject
    public MainRepository(ApiServices apiServices, MovieDao movieDao, BatmanExecutors executors) {
        this.apiServices = apiServices;
        this.movieDao = movieDao;
        this.executors = executors;
    }

    public Observable<SearchList> getSearch(String search) {
        return apiServices.getMovie(search)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io());

    }

    public Observable<Details> getDetails(String imdbID) {
        return apiServices.getDetails(imdbID)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io());

    }

    public Single<List<Movie>> loadLocalMovie() {
        return movieDao.loadAllMovies()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io());
    }

    @SuppressLint("CheckResult")
    public void saveMovieListLocal(List<Movie> list) {
        executors.getDiskIO().execute(() -> movieDao.insert(list));
    }


    public void deleteCachedBookmark() {
        executors.getDiskIO().execute(movieDao::deleteAllCachedMovie);
    }


    public List<Movie> convertSearchToLocalMovie(List<Search> searchList) {
        List<Movie> movieList = new ArrayList<>();
        for (int i = 0; i < searchList.size(); i++) {
            Movie movie = new Movie(i,
                    searchList.get(i).title,
                    searchList.get(i).year,
                    searchList.get(i).imdbID,
                    searchList.get(i).type,
                    searchList.get(i).poster,
                    true
            );
            movieList.add(movie);
        }
        return movieList;
    }

    public SearchList convertLocalMovieToSearchList(List<Movie> movieList) {
        List<Search> list = new ArrayList<>();
        SearchList searchList = new SearchList();
        for (int i = 0; i < movieList.size(); i++) {
            Search search = new Search(
                    movieList.get(i).title,
                    movieList.get(i).year,
                    movieList.get(i).imdbID,
                    movieList.get(i).type,
                    movieList.get(i).poster
            );
            list.add(search);
            searchList = new SearchList(list, String.valueOf(movieList.size()), "True");
        }
        return searchList;
    }

}
