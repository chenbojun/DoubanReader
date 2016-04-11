package com.example.hzchenbojun.doubanreader.view.views;

import android.content.ClipData;
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
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private MyItemClickListener myItemClickListener;
    public BookSet bookSet;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    public MyAdapter(Context context, BookSet bookSet) {
        this.mContext = context;
        this.bookSet = bookSet;
    }

    public void setMyItemClickListener(MyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    public void setDataSet(BookSet bookSet) {
        this.bookSet = bookSet;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM) {
            ItemViewHolder holder = new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_book, parent,
                    false));
            return holder;
        } else {
            FooterViewHolder holder = new FooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.foot_view, parent,
                    false));
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ItemViewHolder) {
            Book book = bookSet.books.get(position);
            Picasso.with(mContext).load(book.image).into(((ItemViewHolder)holder).imageView);
            ((ItemViewHolder)holder).textView_Name.setText(book.getName());
            ((ItemViewHolder)holder).textView_PublishMsg.setText(book.getPublishMsg());
            ((ItemViewHolder)holder).textView_EvaluateMsg.setText(book.getRating());
            ((ItemViewHolder)holder).textView_Price.setText(book.getPrice());
            ((ItemViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myItemClickListener.onItemClick(v, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return bookSet.books.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView_Name;
        private TextView textView_PublishMsg;
        private TextView textView_EvaluateMsg;
        private TextView textView_Price;
        public ItemViewHolder(View view) {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.recycleView_Item_ImageView);
            textView_Name = (TextView)view.findViewById(R.id.recycleView_Item_Name_TextView);
            textView_PublishMsg = (TextView)view.findViewById(R.id.recycleView_Item_PublishMsg_TextView);
            textView_EvaluateMsg = (TextView)view.findViewById(R.id.recycleView_Item_EvaluateMsg_TextView);
            textView_Price = (TextView)view.findViewById(R.id.recycleView_Item_Price_TextView);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        private TextView footer_TextView;
        public FooterViewHolder(View view) {
            super(view);
            footer_TextView = (TextView)view.findViewById(R.id.foot_TextView);
        }
    }

    public interface MyItemClickListener {
        public void onItemClick(View v, int position);
    }
}
