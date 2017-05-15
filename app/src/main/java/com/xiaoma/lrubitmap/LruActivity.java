package com.xiaoma.lrubitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xiaoma.hometest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaoma on 2017/5/13.
 */

public class LruActivity extends AppCompatActivity {

    @Bind(R.id.btn_handler)
    Button btnHandler;
    @Bind(R.id.iv_img)
    ImageView ivImg;
    String mUrl = "https://syimg.sheyuan.com/sywebp/2017/05/15/79305fb3-9178-4696-aa17-50d437ff051d.jpg";
    @Bind(R.id.btn_memory)
    Button btnMemory;
    @Bind(R.id.btn_disk)
    Button btnDisk;
    @Bind(R.id.iv_momory)
    ImageView ivMomory;
    @Bind(R.id.iv_disk)
    ImageView ivDisk;
    private ImageCache mImgCache;
    private DoubleCache doubleCache;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lru);
        ButterKnife.bind(this);
        imgStudy();
    }

    private void imgStudy() {
        ivImg.getHeight();
        ivImg.getWidth();
    }

    @OnClick(R.id.btn_handler)
    public void onViewClicked() {
        Bitmap bitmap;
        doubleCache = new DoubleCache(this);
        setImgCache(doubleCache);
        bitmap = doubleCache.get(mUrl);
        if (null == bitmap) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.viewpager_two);
            doubleCache.put(mUrl, bitmap);
            ivImg.setImageBitmap(bitmap);
        } else {
            ivImg.setImageBitmap(bitmap);
        }
    }

    private void setImgCache(ImageCache cache) {
        mImgCache = cache;
    }

    @OnClick({R.id.btn_memory, R.id.btn_disk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_memory:
                ivMomory.setImageBitmap(doubleCache.getMemoryCache().get(mUrl));
                break;
            case R.id.btn_disk:
                ivDisk.setImageBitmap(doubleCache.getDiskCache().get(mUrl));
                break;
        }
    }
}
