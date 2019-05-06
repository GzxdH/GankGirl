package com.example.gankgirl.net;

import com.example.gankgirl.bean.GirlsBean;
import com.example.gankgirl.commons.Contast;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Entrances {

    private final Retrofit retrofit;

    private static class SingleEntrance {
        private static final Entrances entrances = new Entrances();
    }

    public static Entrances getInstance() {
        return SingleEntrance.entrances;
    }

    private Entrances() {
        OkHttpClient.Builder ob = new OkHttpClient.Builder();
        ob.connectTimeout(Contast.TimeOut, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(ob.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Contast.Girls)
                .build();
    }

    public void getGirls(Subscriber<GirlsBean> girlsBeanSubscriber, int limit, int page) {
        GirlService girlService = retrofit.create(GirlService.class);
        girlService.girlData(limit, page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(girlsBeanSubscriber);
    }
}
