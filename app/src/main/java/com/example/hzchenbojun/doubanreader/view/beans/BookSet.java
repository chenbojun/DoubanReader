package com.example.hzchenbojun.doubanreader.view.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by hzchenbojun on 2016/4/9.
 */


public class BookSet implements Serializable{
    public Integer count;
    public Integer start;
    public Integer total;
    public List<Book> books = new ArrayList<Book>();

}
