package com.example.hzchenbojun.doubanreader.view.views;

import android.app.Activity;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;

import com.example.hzchenbojun.doubanreader.R;
import com.example.hzchenbojun.doubanreader.view.beans.BookSet;
import com.example.hzchenbojun.doubanreader.view.presenters.MainPresenter;

public class MainActivity extends Activity implements MainView{

    private MainPresenter mMainPresenter;
    private MyAdapter myAdapter;
    private SearchView mSearchView;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mMainPresenter = new MainPresenter(this);
        mSearchView = (SearchView)findViewById(R.id.searchView);
        mSearchView.setIconified(false);
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.v("MainActivity", "onQueryTextSubmit");
                mMainPresenter.search();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mRecyclerView = (RecyclerView)findViewById(R.id.recycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(MainActivity.this, new BookSet());
        mRecyclerView.setAdapter(myAdapter);
        //mRecyclerView.addItemDecoration(new MyDecoration(MainActivity.this));
    }
    @Override
    public String getQueryMsg() {
        return mSearchView.getQuery().toString();
    }

    @Override
    public void displayBooks(BookSet bookSet) {
        myAdapter.setDataSet(bookSet);
        for(int i = 0; i < bookSet.books.size(); i++) {
            Log.v("bookset",bookSet.books.get(i).getName());
        }
    }

    @Override
    public void displayError(String errorMsg) {

    }

    @Override
    public void click(int position) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.onDestroy();
    }
}
