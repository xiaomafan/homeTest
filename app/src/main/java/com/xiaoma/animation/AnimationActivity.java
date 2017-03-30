package com.xiaoma.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xiaoma.hometest.BaseActivity;
import com.xiaoma.hometest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deng on 17/3/19.
 */
public class AnimationActivity extends BaseActivity {


    private ViewPager mViewPager;
    private int[] mImgIDs = new int[]{R.mipmap.viewpager_one, R.mipmap.viewpager_two};
    private List<ImageView> mImageView = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        mViewPager = (ViewPager) findViewById(R.id.vp_viewpager);
//        mViewPager.setPageTransformer(true,new DepthPageTransformer());
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mImgIDs.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(AnimationActivity.this);
                imageView.setImageResource(mImgIDs[position]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                mImageView.add(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mImageView.get(position));
            }
        });

    }
}
