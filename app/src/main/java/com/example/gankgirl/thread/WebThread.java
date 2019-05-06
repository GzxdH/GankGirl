package com.example.gankgirl.thread;

import android.util.Log;

import com.example.gankgirl.bean.NetBook;
import com.example.gankgirl.commons.Contast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebThread extends Thread {
    private String TAG = "WebThread";
    private String wUrl;
    private int isWhat;

    public WebThread(String wUrl, int isWhat) {
        this.wUrl = wUrl;
        this.isWhat = isWhat;
    }

    @Override
    public void run() {
        super.run();
        Document doc = null;
        try {
            doc = Jsoup.connect(wUrl).get();
            if (isWhat == Contast.isBook) {
                GetDocContent(doc);
            } else if (isWhat == Contast.isOcto) {
                GetDocImg(doc);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        }
    }

    private String GetDocImg(Document doc) {
        String img = null;
        try {
            Elements bImgs = doc.select("div.item-shell");
            for (Element element : bImgs) {
/**
 * <div class="item-shell">
 *  <div class="item list">
 *   名字
 *   <a name="brennatocat"></a>
 *   路径
 *   <a href="/brennatocat" class="preview-image"> <img height="424" width="424" data-src="/images/Brennatocat.png" alt="the Brennatocat"> </a>
 *  </div>
 *  <div class="footer">
 *   <p class="number">#147</p>
 *   <p class="purchasable"><span>the</span> <a href="/brennatocat">Brennatocat</a> <span>by</span></p>
 *   作者头像和链接
 *   <a href="https://github.com/johncreek" target="_blank"><img src="https://github.com/johncreek.png" alt="Author" width="28" height="28"></a>
 *  </div>
 * </div>
 */
                String img_name = element.select("a").attr("name");
                String img_url = element.select("img").attr("data-src");
                String img_aUrl = element.select("img").attr("src");
                String img_num = element.select("p.number").text();
//                Log.i(TAG, img_num + "<>" + img_name + "<>" + img_url + "<>" + img_aUrl);
            }
            Elements bAuthors = doc.select("div.footer");
            for (int i = 0; i < bAuthors.size(); i++) {
                Log.i(TAG, i + "<>" + bAuthors.get(i).select("a").attr("href"));
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return img;
    }

    private NetBook GetDocContent(Document doc) {
        NetBook netBook = new NetBook();
        try {
            Elements bTitle = doc.select("div.nr_title");
            for (Element eTitle : bTitle) {
                String title = eTitle.select("h3").text();
                netBook.setbChapter(title);
            }
            Element bElement = doc.select("div.nr_page").get(0);
            Elements bLabels = bElement.getElementsByTag("a");
            for (Element label : bLabels) {
                String title = label.select("a").text();
                String url = label.select("a").attr("href");
                Log.i(TAG, title + "<>" + url);
            }
            Element bContent = doc.select("p.articlecontent").get(0);
            String content = bContent.toString()
                    .replace("</p>", "")
                    .replace("<p class=\"articlecontent\" id=\"articlecontent\">", "")
                    .replace("&nbsp;&nbsp;&nbsp;&nbsp;", "\u3000\u3000");
            String content1 = content.replace("<br>", "=");
            String[] content2 = content1.split("=");
            for (String content3 : content2) {
                if (content3 != null && !content3.equals("") && !content3.equals(" ")) {
                    Log.i(TAG, content3);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return netBook;
    }
}
