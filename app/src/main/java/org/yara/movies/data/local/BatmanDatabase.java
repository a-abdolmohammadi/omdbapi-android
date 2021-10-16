package org.yara.movies.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import org.yara.movies.data.local.dao.MovieDao;
import org.yara.movies.data.local.entity.Movie;

@Database(entities = {Movie.class}, version = BatmanDatabase.VERSION, exportSchema = false)
public abstract class BatmanDatabase extends RoomDatabase {

    static final int VERSION = 1;

    public abstract MovieDao getMovieDao();
}
