package com.example.hzchenbojun.doubanreader.home.presenters;

import com.example.hzchenbojun.doubanreader.home.beans.Book;
import com.example.hzchenbojun.doubanreader.home.models.BookModel;
import com.example.hzchenbojun.doubanreader.home.utils.HttpUtil;
import com.example.hzchenbojun.doubanreader.home.views.DetailView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hzchenbojun on 2016/4/11.
 */
public class DetailPresenter {
    private DetailView mDetailView;
    private BookModel mBookModel;

    public DetailPresenter(DetailView detailView) {
        this.mDetailView = detailView;
        this.mBookModel = new BookModel();
    }

    public void showBookDetail(String id) {
        if(mDetailView != null) {
            Book book = mBookModel.getBook(id);
            if(book != null) {
                mDetailView.showDetail(book);
            } else {
                Callback<Book> callback = new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
                        Book book = response.body();
                        mBookModel.saveBook(book);
                        mDetailView.showDetail(book);
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {
                        String errorMsg = t.getMessage();
                        mDetailView.dispalyError(errorMsg);
                    }
                };
                HttpUtil.getInstance().getBookById(id,callback);
            }
        }
    }

    public void onDestroy() {
        mDetailView = null;
    }
}
