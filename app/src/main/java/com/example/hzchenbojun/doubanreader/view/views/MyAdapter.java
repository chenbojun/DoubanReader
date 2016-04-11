package com.example.hzchenbojun.doubanreader.view.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzchenbojun.doubanreader.R;
import com.example.hzchenbojun.doubanreader.view.beans.Book;
import com.example.hzchenbojun.doubanreader.view.beans.BookSet;
import com.squareup.picasso.Picasso;

/**
 * Created by hzchenbojun on 2016/4/9.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context mContext;
    private MyItemClickListener myItemClickListener;
    public BookSet bookSet;

    public MyAdapter(Context context, BookSet bookSet) {
        this.mContext = context;
        this.bookSet = bookSet;
    }

    public void setMyItemClickListener(MyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    public void setDataSet(BookSet bookSet) {
        this.bookSet = bookSet;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_book, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Book book = bookSet.books.get(position);
        Picasso.with(mContext).load(book.image).into(holder.imageView);
        holder.textView_Name.setText(book.getName());
        holder.textView_PublishMsg.setText(book.getPublishMsg());
        holder.textView_EvaluateMsg.setText(book.getRating());
        holder.textView_Price.setText(book.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myItemClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookSet.books.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView_Name;
        private TextView textView_PublishMsg;
        private TextView textView_EvaluateMsg;
        private TextView textView_Price;
        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.recycleView_Item_ImageView);
            textView_Name = (TextView)view.findViewById(R.id.recycleView_Item_Name_TextView);
            textView_PublishMsg = (TextView)view.findViewById(R.id.recycleView_Item_PublishMsg_TextView);
            textView_EvaluateMsg = (TextView)view.findViewById(R.id.recycleView_Item_EvaluateMsg_TextView);
            textView_Price = (TextView)view.findViewById(R.id.recycleView_Item_Price_TextView);
        }
    }

    public interface MyItemClickListener {
        public void onItemClick(View v, int position);
    }
}
