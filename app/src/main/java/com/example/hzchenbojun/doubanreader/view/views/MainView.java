package com.example.hzchenbojun.doubanreader.view.views;

import com.example.hzchenbojun.doubanreader.view.beans.BookSet;

/**
 * Created by hzchenbojun on 2016/4/9.
 */
public interface MainView {
    public String getQueryMsg();
    public void displayBooks(BookSet books);
    public void displayError(String errorMsg);
    public void showDetailActivity(String id);
}
