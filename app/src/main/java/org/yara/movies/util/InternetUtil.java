package org.yara.movies.util;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.lifecycle.LiveData;
import java.util.Objects;

public class InternetUtil extends LiveData<Boolean> {

    private static Application application;

    public static void init(Application application) {
        InternetUtil.application = application;
    }

    public static Boolean isInternetOn() {

        ConnectivityManager cm = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = Objects.requireNonNull(cm).getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
