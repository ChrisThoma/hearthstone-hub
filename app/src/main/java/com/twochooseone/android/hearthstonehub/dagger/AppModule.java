package com.twochooseone.android.hearthstonehub.dagger;

import android.app.Application;
import android.content.Context;
import dagger.Module;

import com.twochooseone.android.hearthstonehub.Datastore;
import com.twochooseone.android.hearthstonehub.MainApp;
import com.squareup.okhttp.OkHttpClient;
import com.twochooseone.android.hearthstonehub.activity.HomeActivity;
import com.twochooseone.android.hearthstonehub.fragment.ArenaTiersFragment;
import com.twochooseone.android.hearthstonehub.fragment.CardsListFragment;
import com.twochooseone.android.hearthstonehub.fragment.NaxxramasFragment;
import com.twochooseone.android.hearthstonehub.fragment.YourDecksFragment;

import java.net.URL;

import javax.inject.Singleton;

import dagger.Provides;

@Module(injects = {
        Injector.class,
        MainApp.class,
        HomeActivity.class,
        CardsListFragment.class,
        YourDecksFragment.class,
        ArenaTiersFragment.class,
        NaxxramasFragment.class,
}, library = true, complete = true)
public class AppModule {
    private final Application application;
    private final Injector injector;

    public AppModule(Application application) {
        this.application = application;
        this.injector = new Injector(application);
    }

    /**
     * Allow the application context to be injected but require that it be annotated with {@link
     * ForApplication @Annotation} to explicitly differentiate it from an activity context.
     */
    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    Injector providesInjector() {
        return injector;
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient() {
        OkHttpClient httpClient = new OkHttpClient();
        URL.setURLStreamHandlerFactory(httpClient);
        return httpClient;
    }

    @Provides
    @Singleton
    Datastore providesDatastore() {
        return new Datastore(application);
    }

}

