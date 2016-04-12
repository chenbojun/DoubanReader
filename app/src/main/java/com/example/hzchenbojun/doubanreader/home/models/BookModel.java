package com.example.hzchenbojun.doubanreader.home.models;

import com.example.hzchenbojun.doubanreader.home.beans.Book;
import com.example.hzchenbojun.doubanreader.home.utils.DataBaseUtil;

/**
 * Created by hzchenbojun on 2016/4/11.
 */
public class BookModel {
    private static Book book;
    public Book getBook(String id) {
        return DataBaseUtil.getInstance().getBookById(id);
    }
    public void saveBook(Book book) {

    }
}
