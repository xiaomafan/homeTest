package com.xiaoma.hometest;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by xiaoma on 2017/4/6.
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                // 添加你的配置需求
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
