package com.xiaoma.imageloader;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.xiaoma.base.BaseActivity;
import com.xiaoma.hometest.LogHelp;
import com.xiaoma.hometest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaoma on 2017/4/6.
 */

public class ImgLoaderActivity extends BaseActivity {

    @Bind(R.id.btn_showpic)
    Button mShowPic;
    @Bind(R.id.iv_img)
    ImageView mImg;
    private static final String Tag="ImgLoaderActivity";
    String mUrl="http://syimg.sheyuan.com/flow_admin/2016/12/aa4f7e4f-1efa-4b37-b584-6be792b6055302.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imgloader);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_showpic)
    public void onClick() {
        ImageLoader.getInstance().displayImage(mUrl,mImg);

        ImageLoader.getInstance().loadImage(mUrl, new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                LogHelp.e(Tag,imageUri+"/"+loadedImage);
                mImg.setImageBitmap(loadedImage);
            }
        });
    }
}
