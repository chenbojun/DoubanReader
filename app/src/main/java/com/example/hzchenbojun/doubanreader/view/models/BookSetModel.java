package com.example.hzchenbojun.doubanreader.view.models;

import com.example.hzchenbojun.doubanreader.view.beans.BookSet;

/**
 * Created by hzchenbojun on 2016/4/9.
 */
public class BookSetModel {
    private static BookSet bookSet;
    public void saveBookSet(BookSet bookSet) {
        this.bookSet = bookSet;
    }
    public BookSet getBookSet() {
        return bookSet;
    }
}
