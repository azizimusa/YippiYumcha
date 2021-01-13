package yumcha.toga;

import android.app.Application;

import timber.log.Timber;

public class MyApp extends Application {

    private static MyApp instance;

    public static synchronized MyApp getInstance() {
        return instance;
    }

    private MyRetrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        Timber.plant(new Timber.DebugTree());
    }

    public MyRetrofit getRestApi() {

        if (retrofit == null) {
            retrofit = MyRetrofit.Factory.create();
        }

        return retrofit;
    }
}
