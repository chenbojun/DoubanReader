package com.example.hzchenbojun.doubanreader.view.views;

import com.example.hzchenbojun.doubanreader.view.beans.Book;

/**
 * Created by hzchenbojun on 2016/4/11.
 */
public interface DetailView {
    public void showDetail(Book book);
    public void dispalyError(String errorMsg);
}
