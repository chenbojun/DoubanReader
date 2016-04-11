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
        mBooksModel.queryMsg = mMainView.getQueryMsg();
        Callback<BookSet> callback = new Callback<BookSet>() {
            @Override
            public void onResponse(Call<BookSet> call, Response<BookSet> response) {
                BookSet bookSet = response.body();
                mBooksModel.saveBookSet(bookSet);
                if(mMainView != null) {
                    mMainView.displayBooks(bookSet);
                }
            }
            @Override
            public void onFailure(Call<BookSet> call, Throwable t) {
                String errorMsg = t.getMessage();
                if(mMainView != null) {
                    mMainView.displayError(errorMsg);
                }
            }
        };
        HttpUtil.getInstance().search(mBooksModel.queryMsg , 0, 10, callback);
    }
    public void loadMore() {
        String queryMsg = mBooksModel.queryMsg;
        int start = mBooksModel.getBookSet().count;
        int count = 10;
        Callback<BookSet> callback = new Callback<BookSet>() {
            @Override
            public void onResponse(Call<BookSet> call, Response<BookSet> response) {
                BookSet bookSet = response.body();
                mBooksModel.getBookSet().books.addAll(bookSet.books);
                mBooksModel.getBookSet().count += bookSet.count;
                if(mMainView != null) {
                    mMainView.loadMore(bookSet);
                }
            }
            @Override
            public void onFailure(Call<BookSet> call, Throwable t) {
                String errorMsg = t.getMessage();
                if(mMainView != null) {
                    mMainView.displayError(errorMsg);
                }
            }
        };
        HttpUtil.getInstance().search(queryMsg, start, count, callback);

    }
    public void showBookDetail(int position){
        String id = mBooksModel.getBookSet().books.get(position).id;
        mMainView.showDetailActivity(id);
    }
    public void onDestroy() {
        mMainView = null;
    }
}
