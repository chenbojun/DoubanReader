package com.example.hzchenbojun.doubanreader.home.utils;

import com.example.hzchenbojun.doubanreader.home.beans.Book;
import com.example.hzchenbojun.doubanreader.home.beans.BookSet;

/**
 * Created by hzchenbojun on 2016/4/11.
 */
public class DataBaseUtil {
    private static DataBaseUtil instance;
    private static BookSet bookSet;

    public static DataBaseUtil getInstance() {
        if(instance == null) {
            instance = new DataBaseUtil();
        }
        return instance;
    }
    public void saveBookSet(BookSet bookSet) {
        this.bookSet = bookSet;
    }
    public BookSet getBookSet() {
        return bookSet;
    }
    public Book getBookById(String id) {
        for(Book book : bookSet.books) {
            if(book.id.equals(id)) {
                return book;
            }
        }
        return null;
    }
}
