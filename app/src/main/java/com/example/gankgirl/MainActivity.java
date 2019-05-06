package com.example.gankgirl;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.gankgirl.adapter.MyRecyclerAdapter;
import com.example.gankgirl.bean.GirlsBean;
import com.example.gankgirl.commons.Contast;
import com.example.gankgirl.net.Entrances;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private String TAG = "MainActivity";
    private int limit = 10;
    private int page = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == Contast.isGirls) {
                if (isRefresh) {
                    page += 1;
                }
                if (isLoad) {
                    page += 1;
                }
                getGirls(limit, page);
            }
        }
    };
    private RecyclerView recyclerView;
    private MyRecyclerAdapter recyclerAdapter;
    private boolean isRefresh = false;
    private boolean isLoad = false;
//    private List<GirlsBean.ResultsBean> resultsBeans = new ArrayList<>();
    private BGARefreshLayout bga_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        handler.sendEmptyMessage(Contast.isGirls);
        bga_refresh = findViewById(R.id.bga_refresh);
        setBGARefresh();
    }

    private void setBGARefresh() {
        // 为BGARefreshLayout 设置代理
        bga_refresh.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        // 设置下拉刷新和上拉加载更多的风格
        bga_refresh.setRefreshViewHolder(refreshViewHolder);
    }

    private void getGirls(int limit, final int page) {
        Entrances entrances = Entrances.getInstance();
        entrances.getGirls(new Subscriber<GirlsBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("net error", e.toString());
            }

            @Override
            public void onNext(final GirlsBean girlsBean) {
//                resultsBeans.addAll(girlsBean.getResults());
                recyclerView.setLayoutManager(new GridLayoutManager(App.getContext(), 1));
                recyclerAdapter = new MyRecyclerAdapter(R.layout.layout_top, girlsBean.getResults());
                recyclerView.setAdapter(recyclerAdapter);
                if (isRefresh) {
                    isRefresh = false;
                    bga_refresh.endRefreshing();
                    recyclerAdapter.addData(girlsBean.getResults());
                }
                if (isLoad) {
                    isLoad = false;
                    bga_refresh.endLoadingMore();
                    recyclerAdapter.addData(girlsBean.getResults());
                }
                recyclerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Log.i(TAG, girlsBean.getResults().get(position).getWho() + "<>" + girlsBean.getResults().get(position).getUrl());
                    }
                });
            }
        }, limit, page);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        isRefresh = true;
        handler.sendEmptyMessage(Contast.isGirls);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        isLoad = true;
        handler.sendEmptyMessage(Contast.isGirls);
        return false;
    }
}
