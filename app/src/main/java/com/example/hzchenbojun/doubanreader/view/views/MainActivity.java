package com.example.hzchenbojun.doubanreader.view.views;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.hzchenbojun.doubanreader.R;
import com.example.hzchenbojun.doubanreader.view.beans.BookSet;
import com.example.hzchenbojun.doubanreader.view.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mMainPresenter;
    private MyAdapter myAdapter;
    private SearchView mSearchView;
    private RecyclerView mRecyclerView;
    private AlertDialog loadingAlertDialog;
    private boolean isSearch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mMainPresenter = new MainPresenter(this);
        loadingAlertDialog = new AlertDialog.Builder(this).setMessage("正在努力加载···").create();
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
        myAdapter.setMyItemClickListener(new MyAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                mMainPresenter.showBookDetail(position);
            }
        });
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager lm = (LinearLayoutManager)recyclerView.getLayoutManager();
                int lastPosition = lm.findLastCompletelyVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        &&  lastPosition + 1 == myAdapter.getItemCount() && isSearch) {
                    mMainPresenter.loadMore();
                }
            }
        });
        //mRecyclerView.addItemDecoration(new MyDecoration(MainActivity.this));
    }
    @Override
    public String getQueryMsg() {
        return mSearchView.getQuery().toString();
    }

    @Override
    public void displayBooks(BookSet bookSet) {
        if(bookSet == null) {
            return;
        }
        if(bookSet.books.size() == 0) {
            Toast toast = Toast.makeText(this, "抱歉没有您想要的结果~", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            isSearch = true;
            myAdapter.setDataSet(bookSet);
            myAdapter.notifyDataSetChanged();
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void displayError(String errorMsg) {
        Toast toast = Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void showDetailActivity(String id) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
    @Override
    public void loadMore(BookSet bookSet) {
        myAdapter.bookSet.books.addAll(bookSet.books);
        myAdapter.bookSet.count += bookSet.count;
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        loadingAlertDialog.show();
    }

    @Override
    public void hideProgress() {
        loadingAlertDialog.dismiss();
        mSearchView.clearFocus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSearchView.clearFocus();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.onDestroy();
    }
}
