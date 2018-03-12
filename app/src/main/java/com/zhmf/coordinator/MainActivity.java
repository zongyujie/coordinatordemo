package com.zhmf.coordinator;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.zhmf.coordinator.adapter.BaseFragmentAdapter;
import com.zhmf.coordinator.listenter.AppBarStaterChangeListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager mViewPager;
    List<Fragment> mFragments;
    Toolbar mToolbar;
    AppBarLayout appbar;
    LinearLayout l_top;
    CollapsingToolbarLayout collapsing;
    String[]  mTitles=new String[]{
            "测试1","测试2","测试3"
    };
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        collapsing=findViewById(R.id.collapsing);
        collapsing.setExpandedTitleColor(getResources().getColor(R.color.color_transparent_00000000));
        collapsing.setCollapsedTitleTextColor(getResources().getColor(R.color.color_black_000000));
        appbar=findViewById(R.id.appbar);
        l_top=findViewById(R.id.l_top);
        appbar.addOnOffsetChangedListener(new AppBarStaterChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if( state == State.EXPANDED ) {
                    l_top.setVisibility(View.VISIBLE);
                    //展开状态
                }else if(state == State.COLLAPSED){
                    l_top.setVisibility(View.INVISIBLE);
                    //折叠状态
                }else {
                    //中间状态
                }
            }
        });
        mToolbar.setTitle("唐嫣");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupViewPager();
    }


    private void setupViewPager() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        mFragments=new ArrayList<>();
        for(int i=0;i<mTitles.length;i++){
            ListFragment listFragment = ListFragment.newInstance(mTitles[i]);
            mFragments.add(listFragment);
        }
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(),mFragments,mTitles);
        viewPager.setAdapter(adapter);
    }
}
