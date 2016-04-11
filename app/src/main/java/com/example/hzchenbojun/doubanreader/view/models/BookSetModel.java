package com.example.hzchenbojun.doubanreader.view.models;

import com.example.hzchenbojun.doubanreader.view.beans.BookSet;
import com.example.hzchenbojun.doubanreader.view.utils.ACache;
import com.example.hzchenbojun.doubanreader.view.utils.ContextUtil;
import com.example.hzchenbojun.doubanreader.view.utils.DataBaseUtil;

/**
 * Created by hzchenbojun on 2016/4/9.
 */
public class BookSetModel {
    public void saveBookSet(BookSet bookSet) {
        DataBaseUtil.getInstance().saveBookSet(bookSet);
    }
    public BookSet getBookSet() {
        return DataBaseUtil.getInstance().getBookSet();
    }
}
