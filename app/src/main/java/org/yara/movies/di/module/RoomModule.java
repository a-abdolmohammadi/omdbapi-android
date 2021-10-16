package org.yara.movies.di.module;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.yara.movies.data.local.BatmanDatabase;
import org.yara.movies.data.local.dao.MovieDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class RoomModule {

    private static BatmanDatabase INSTANCE;


    public synchronized static BatmanDatabase getInstance(Application context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

   public static BatmanDatabase buildDatabase(Application context) {
        return Room.databaseBuilder(context,
                BatmanDatabase.class,
                "my-database")
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                    }
                })
                .build();
    }

    @Provides
    @Singleton
    BatmanDatabase provideBatmanDatabase(Application mApplication) {
        return buildDatabase(mApplication);
    }

    @Provides
    @Singleton
    MovieDao provideMovieDao(BatmanDatabase database) {
        return database.getMovieDao();
    }
}
