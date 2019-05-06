package com.example.gankgirl.net;

import com.example.gankgirl.bean.GirlsBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GirlService {
    @GET("福利/{limit}/{page}")
    Observable<GirlsBean> girlData(
            @Path("limit") int limit,
            @Path("page") int page);
}
