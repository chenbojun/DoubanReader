package com.example.hzchenbojun.doubanreader.home.models;

import com.example.hzchenbojun.doubanreader.home.beans.BookSet;
import com.example.hzchenbojun.doubanreader.home.utils.DataBaseUtil;

/**
 * Created by hzchenbojun on 2016/4/9.
 */
public class BookSetModel {
    public String queryMsg;
    public void saveBookSet(BookSet bookSet) {
        DataBaseUtil.getInstance().saveBookSet(bookSet);
    }
    public BookSet getBookSet() {
        return DataBaseUtil.getInstance().getBookSet();
    }
}
