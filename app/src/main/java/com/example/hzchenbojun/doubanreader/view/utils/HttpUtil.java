package com.example.hzchenbojun.doubanreader.view.utils;

import com.example.hzchenbojun.doubanreader.view.apis.DoubanBookService;
import com.example.hzchenbojun.doubanreader.view.beans.Book;
import com.example.hzchenbojun.doubanreader.view.beans.BookSet;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hzchenbojun on 2016/4/9.
 */
public class HttpUtil {
    private static HttpUtil mInstance;
    private Retrofit mRetrofit;
    private DoubanBookService mDounbanBookService;

    public static HttpUtil getInstance() {
        if(mInstance == null) {
            mInstance = new HttpUtil();
        }
        return mInstance;
    }

    private HttpUtil() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("https://api.douban.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mDounbanBookService = retrofit.create(DoubanBookService.class);
    }

    public void search(String queryMsg, final Callback<BookSet> callback) {
        Call<BookSet> call = mDounbanBookService.search(queryMsg, 0, 10);
        call.enqueue(callback);
    }

    public void getBookById(String id, final Callback<Book> callback) {
        Call<Book> call = mDounbanBookService.getDetailById(id);
        call.enqueue(callback);
    }

}
