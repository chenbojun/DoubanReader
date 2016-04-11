package com.example.hzchenbojun.doubanreader.view.apis;

import com.example.hzchenbojun.doubanreader.view.beans.Book;
import com.example.hzchenbojun.doubanreader.view.beans.BookSet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by hzchenbojun on 2016/4/9.
 */
public interface DoubanBookService {
    @GET("v2/book/search")
    Call<BookSet> search(@Query("q") String q, @Query("start") int start, @Query("count") int count) ;

    @GET("v2/book/{book_id}")
    Call<Book> getDetailById(@Path("book_id") String book_id);
}
