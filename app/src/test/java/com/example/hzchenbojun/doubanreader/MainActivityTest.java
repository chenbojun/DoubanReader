package com.example.hzchenbojun.doubanreader;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.example.hzchenbojun.doubanreader.home.views.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;
/**
 * Created by hzchenbojun on 2016/4/12.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    private Activity activity;
    private SearchView searchView;
    private RecyclerView mRecyclerView;
    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(MainActivity.class);
        searchView = (SearchView)activity.findViewById(R.id.searchView);
        mRecyclerView = (RecyclerView)activity.findViewById(R.id.recycleView);
    }
    @Test
    public void titleIsCorrect() throws Exception {
        assertTrue(activity.getTitle().toString().equals("DoubanReader"));
    }

    @Test
    public void testGetQueryMsg() throws Exception {
        searchView.setQuery("android", false);
        assertTrue(searchView.getQuery().toString().equals("android"));
    }

}
