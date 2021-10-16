package org.yara.movies.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BatmanExecutors {

    private final Executor diskIO;

    @Inject
    public BatmanExecutors() {
        this.diskIO = Executors.newSingleThreadExecutor();
    }

    public Executor getDiskIO() {
        return diskIO;
    }

}
