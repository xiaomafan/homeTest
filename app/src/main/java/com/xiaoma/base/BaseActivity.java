package com.xiaoma.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.TextView;

import com.xiaoma.hometest.R;

/**
 * Created by deng on 17/3/19.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatus();
    }
    
    public void setStatus(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                //将侧边栏顶部延伸至status bar
//                mDrawerLayout.setFitsSystemWindows(true);
                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
//                mDrawerLayout.setClipToPadding(false);
            }
        }
    }
    
    
    protected void setTitle(String title){
        try {
            ((TextView) findViewById(R.id.head_title)).setText(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void setTitleBg(int color){
        try {
            ((Toolbar) findViewById(R.id.head_toolbar)).setBackgroundResource(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
