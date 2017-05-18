package com.xiaoma.fourcomp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.xiaoma.base.BaseActivity;
import com.xiaoma.hometest.LogHelp;
import com.xiaoma.hometest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaoma on 2017/4/5.
 */

public class FourActivity extends BaseActivity {


    @Bind(R.id.btn_operate)
    Button mStartService;
    @Bind(R.id.btn_stop)
    Button mStopService;
    @Bind(R.id.btn_bind)
    Button btnBind;
    @Bind(R.id.btn_unbind)
    Button btnUnbind;
    private Intent bindIntent;
    private final static String Tag="MyService";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        ButterKnife.bind(this);
        LogHelp.e(Tag,"FourActivity="+Thread.currentThread().getId()+"/"+Thread.currentThread().getName());
    }


    @OnClick({R.id.btn_operate, R.id.btn_stop,R.id.btn_bind, R.id.btn_unbind})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_operate:
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.btn_stop:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.btn_bind:
                bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
//                unbindService(connection);
                myBinder.startDown("abc");
                break;
        }
    }

    private MyService.MyBinder myBinder;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            LogHelp.e(Tag, "onServiceConnected()");
            myBinder = (MyService.MyBinder) service;
            myBinder.startDown("abc");
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            LogHelp.e(Tag, "onServiceDisconnected()");
            
        }
        
    };
   

}
