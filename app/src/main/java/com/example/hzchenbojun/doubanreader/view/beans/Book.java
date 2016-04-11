package com.example.hzchenbojun.doubanreader.view.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by hzchenbojun on 2016/4/9.
 */
public class Book implements Serializable{
    public Rating rating;
    public String subtitle;
    public List<String> author = new ArrayList<String>();
    public String pubdate;
    public List<Tag> tags = new ArrayList<Tag>();
    public String originTitle;
    public String image;
    public String binding;
    public List<Object> translator = new ArrayList<Object>();
    public String catalog;
    public String pages;
    public Images images;
    public String alt;
    public String id;
    public String publisher;
    public String isbn10;
    public String isbn13;
    public String title;
    public String url;
    public String altTitle;
    public String authorIntro;
    public String summary;
    public String price;
    public String ebookUrl;
    public String ebookPrice;
    public Series series;

    public String getName() {
        String result = "";
        result += title;
        if(!subtitle.equals("")) {
            result += "：" + subtitle;
        }
        return result;
    }

    public String getPublishMsg() {
        String result = "";
        int size = author.size();
        for(int i = 0; i < size - 1; i++) {
            result += author.get(i) + "、";
        }
        if(size > 0) {
            result += author.get(size - 1) + "/";
        }
        result += publisher + "/";
        result += pubdate;
        return result;
    }

    public String getRating() {
        String result = "";
        result += "评分：" + rating.average + "(" + rating.numRaters + "人评价" + "）";
        return result;
    }

    public String getPrice() {
        return "价格：" + price;
    }

    public String getAuthor() {
        String result = "";
        int size = author.size();
        for(int i = 0; i < size - 1; i++) {
            result += author.get(i) + "、";
        }
        if(size > 0) {
            result += author.get(size - 1) + "/";
        }
        return result;
    }
}
