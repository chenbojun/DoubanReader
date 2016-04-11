package com.example.hzchenbojun.doubanreader.view.views;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzchenbojun.doubanreader.R;
import com.example.hzchenbojun.doubanreader.view.beans.Book;
import com.example.hzchenbojun.doubanreader.view.presenters.DetailPresenter;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailActivity extends Activity implements DetailView{
    private DetailPresenter mDetailPresenter;
    private String id;
    private TextView textView_title;
    private TextView textView_author;
    private TextView textView_publisher;
    private TextView textView_subtitle;
    private TextView textView_pubdate;
    private TextView textView_pages;
    private TextView textView_price;
    private TextView textView_isbn;
    private TextView textView_summary;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDetailPresenter.showBookDetail(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDetailPresenter.onDestroy();
    }

    @Override
    public void showDetail(Book book) {
        Picasso.with(this).load(book.image).into(imageView);
        textView_title.setText(book.title);
        textView_author.setText(book.getAuthor());
        textView_publisher.setText(book.publisher);
        textView_subtitle.setText(book.subtitle);
        textView_pubdate.setText(book.pubdate);
        textView_pages.setText(book.pages);
        textView_price.setText(book.price);
        textView_isbn.setText(book.isbn13);
        textView_summary.setText(book.summary);
    }

    @Override
    public void dispalyError(String errorMsg) {

    }

    private void init() {
        mDetailPresenter = new DetailPresenter(this);
        id = getIntent().getStringExtra("id");
        textView_title = (TextView)findViewById(R.id.detail_title_TextView);
        textView_author = (TextView)findViewById(R.id.detail_author_TextView);
        textView_publisher = (TextView)findViewById(R.id.detail_publisher_TextView);
        textView_subtitle = (TextView)findViewById(R.id.detail_subtitle_TextView);
        textView_pubdate = (TextView)findViewById(R.id.detail_pubdate_TextView);
        textView_pages = (TextView)findViewById(R.id.detail_pages_TextView);
        textView_price = (TextView)findViewById(R.id.detail_price_TextView);
        textView_isbn = (TextView)findViewById(R.id.detail_isbn_TextView);
        textView_summary = (TextView)findViewById(R.id.detail_summary_TextView);
        imageView = (ImageView)findViewById(R.id.detail_ImageView);
    }
}
