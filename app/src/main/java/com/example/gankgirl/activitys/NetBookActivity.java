package com.example.gankgirl.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.example.gankgirl.R;
import com.example.gankgirl.commons.Contast;
import com.example.gankgirl.thread.WebThread;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class NetBookActivity extends AppCompatActivity {
    private String bUrl = "http://www.fszww.com";
    private String bLocation = "/0/1/1.html";
    private String TAG = "NetBookActivity";
    private WebThread webThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_book);
//        WebView wv_nb = findViewById(R.id.wv_nb);
//        wv_nb.loadUrl("http://www.fszww.com/0/1/1.html");
        webThread = new WebThread(bUrl + bLocation, Contast.isBook);
        webThread.start();
//        new Thread() {
//            @Override
//            public void run() {
//                String url = bUrl + bLocation;
//                Document doc = null;
//                try {
//                    doc = Jsoup.connect(url).get();
//                    GetDocContent(doc);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Log.e(TAG, "19" + e.toString());
//                }
//            }
//        }.start();
    }

//    private void GetDocContent(Document doc) {
//        try {
//            Elements bTitle = doc.select("div.nr_title");
//            for (Element eTitle : bTitle) {
//                String title = eTitle.select("h3").text();
//                Log.i(TAG, title);
//            }
//            Element bElement = doc.select("div.nr_page").get(0);
//            Elements bLabels = bElement.getElementsByTag("a");
//            for (Element label : bLabels) {
//                String title = label.select("a").text();
//                String url = label.select("a").attr("href");
//                Log.i(TAG, title + "<>" + url);
//            }
//            Element bContent = doc.select("p.articlecontent").get(0);
//            String content = bContent.toString()
//                    .replace("</p>", "")
//                    .replace("<p class=\"articlecontent\" id=\"articlecontent\">", "")
//                    .replace("&nbsp;&nbsp;&nbsp;&nbsp;", "\u3000\u3000");
//            String content1 = content.replace("<br>", "=");
//            String[] content2 = content1.split("=");
//            for (String content3 : content2) {
//                if (content3 != null && !content3.equals("") && !content3.equals(" ")) {
//                    Log.i(TAG, content3);
//                }
//            }
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webThread.isAlive()) {
            webThread.interrupt();
        }
    }
}
