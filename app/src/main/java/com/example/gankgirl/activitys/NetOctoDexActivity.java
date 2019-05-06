package com.example.gankgirl.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gankgirl.R;
import com.example.gankgirl.commons.Contast;
import com.example.gankgirl.thread.WebThread;

public class NetOctoDexActivity extends AppCompatActivity {

    private WebThread webThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_octo_dex);
        webThread = new WebThread("https://octodex.github.com", Contast.isOcto);
        webThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webThread.isAlive()) {
            webThread.interrupt();
        }
    }
}