package org.yara.movies.data.local.dao;

import androidx.room.Dao;
import androidx.room.Query;

import org.yara.movies.data.local.entity.Movie;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface MovieDao extends BaseDao<Movie> {

    @Query("SELECT * FROM Movie")
    Single<List<Movie>> loadAllMovies();

    @Query(("DELETE FROM Movie WHERE cached=1"))
    void deleteAllCachedMovie();

}
