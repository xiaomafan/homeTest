package com.xiaoma.hometest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.xiaoma.animation.AnimationActivity;
import com.xiaoma.base.BaseActivity;
import com.xiaoma.customview.CustomViewActivity;

import com.xiaoma.customview.WeatherActivity;
import com.xiaoma.matchseven.MatchSeven;
import com.xiaoma.shapeview.ShapeActivity;


import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private RecyclerView mRecycleView;
    private String[] mHomeItems;
    private ArrayList<String> mDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycleView = (RecyclerView) findViewById(R.id.rv_recycleview);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mHomeItems = getResources().getStringArray(R.array.homeitems);
        initItems();
        HomeAdapter adapter = new HomeAdapter(this, mDatas);
        mRecycleView.setAdapter(adapter);
        adapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        toNextClazz(AnimationActivity.class);
                        break;
                    case 1:
                        toNextClazz(CustomViewActivity.class);
                        break;
                    case 2:
                        toNextClazz(MatchSeven.class);
                        break;
                    case 3:
                        toNextClazz(ShapeActivity.class);
                        break;
                    case 4:
                        toNextClazz(WeatherActivity.class);
                        break;
                    case 5:
                        Toast.makeText(MainActivity.this,"待学习内容",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void toNextClazz(Class clazz) {
        Intent intent = new Intent(MainActivity.this, clazz);
        startActivity(intent);
    }

    private void initItems() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < mHomeItems.length; i++) {
            mDatas.add(mHomeItems[i]);
        }
    }
}
