package com.xiaoma.hometest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import com.xiaoma.animation.AnimationActivity;
import com.xiaoma.base.BaseActivity;
import com.xiaoma.customview.CustomViewActivity;
import com.xiaoma.customview.WeatherActivity;
import com.xiaoma.fourcomp.FourActivity;
import com.xiaoma.imageloader.ImgLoaderActivity;
import com.xiaoma.lrubitmap.LruActivity;
import com.xiaoma.matchseven.MatchSeven;
import com.xiaoma.okhttp.OkhttpActivity;
import com.xiaoma.rxjava.RxjavaActivity;
import com.xiaoma.shapeview.ShapeActivity;
import com.xiaoma.sort.SortActivity;
import com.xiaoma.status.StatusActivity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private RecyclerView mRecycleView;
    private String[] mHomeItems;
    private ArrayList<String> mDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitleBg(R.color.red);
        setTitle("欢迎加入小马帮");
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
                    case 0: //viewpager动画
                        toNextClazz(AnimationActivity.class);
                        break;
                    case 1: //自定义view
                        toNextClazz(CustomViewActivity.class);
                        break;
                    case 2: //适配7.0
                        toNextClazz(MatchSeven.class);
                        break;
                    case 3: //自定义画图
                        toNextClazz(ShapeActivity.class);
                        break;
                    case 4:  //服务学习
                        toNextClazz(FourActivity.class);
                        break;
                    case 5: //Imageloader学习
                        toNextClazz(ImgLoaderActivity.class);
                        break;
                    case 6:  //Rxjava学习
                        toNextClazz(RxjavaActivity.class);
                        break;
                    case 7: //排序
                        toNextClazz(SortActivity.class);
                        break;
                    case 8: //天气自定义
                        toNextClazz(WeatherActivity.class);
                        break;
                    case 9: //okhttp网络请求
                        toNextClazz(OkhttpActivity.class);
                        break;
                    case 10: //状态栏学习
                        toNextClazz(StatusActivity.class);
                        break;
                    case 11: //图片缓存
                        toNextClazz(LruActivity.class);
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

    @Override
    protected void onStart() {
        super.onStart();
        ViewTreeObserver treeObserver = mRecycleView.getViewTreeObserver();
        treeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mRecycleView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int measuredHeight = mRecycleView.getMeasuredHeight();//获取高度
//  mRecycleView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        
        mRecycleView.post(new Runnable() {
            @Override
            public void run() {
                int measuredHeight = mRecycleView.getMeasuredHeight(); //获取高度
            }
        });
    }
}
