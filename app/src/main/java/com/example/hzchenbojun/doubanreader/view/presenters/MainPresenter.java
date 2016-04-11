package com.example.hzchenbojun.doubanreader.view.presenters;

import android.util.Log;

import com.example.hzchenbojun.doubanreader.view.beans.BookSet;
import com.example.hzchenbojun.doubanreader.view.models.BookSetModel;
import com.example.hzchenbojun.doubanreader.view.utils.HttpUtil;
import com.example.hzchenbojun.doubanreader.view.views.MainView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hzchenbojun on 2016/4/9.
 */
public class MainPresenter {
    private MainView mMainView;
    private BookSetModel mBooksModel;

    public MainPresenter(MainView mMainView) {
        this.mMainView = mMainView;
        this.mBooksModel = new BookSetModel();
    }
    public void search() {
        String queryMsg = mMainView.getQueryMsg();
        Callback<BookSet> callback = new Callback<BookSet>() {
            @Override
            public void onResponse(Call<BookSet> call, Response<BookSet> response) {
                Log.v("MainPresenter", "success!!!");
                BookSet bookSet = response.body();
                mBooksModel.saveBookSet(bookSet);
                if(mMainView != null) {
                    mMainView.displayBooks(bookSet);
                }
            }
            @Override
            public void onFailure(Call<BookSet> call, Throwable t) {
                Log.v("MainPresenter", "fail!!!");
                String errorMsg = t.getMessage();
                if(mMainView != null) {
                    mMainView.displayError(errorMsg);
                }
            }
        };
        HttpUtil.getInstance().search(queryMsg, callback);
    }
    public void onDestroy() {
        mMainView = null;
    }
}
