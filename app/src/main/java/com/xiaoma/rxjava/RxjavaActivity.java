package com.xiaoma.rxjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoma.hometest.BaseActivity;
import com.xiaoma.hometest.LogHelp;
import com.xiaoma.hometest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by xiaoma on 2017/4/25.
 */

public class RxjavaActivity extends BaseActivity {

    @Bind(R.id.btn_click)
    Button btnClick;
    @Bind(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_click)
    public void onViewClicked() {
        //观察者
      Observer<String> observer=new Observer<String>() {
            @Override
            public void onCompleted() {
                LogHelp.e("xiaoma","observer-onCompleted"+Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                LogHelp.e("xiaoma","observer-onError"+e.getMessage()+Thread.currentThread().getName());
            }

            @Override
            public void onNext(String s) {
                LogHelp.e("xiaoma","observer-onNext"+s+Thread.currentThread().getName());
            }
        };

       Subscriber subscriber= new Subscriber<String>() {
            @Override
            public void onCompleted() {
                LogHelp.e("xiaoma","subscriber-onCompleted"+Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                LogHelp.e("xiaoma","subscriber-onError"+e.getMessage()+Thread.currentThread().getName());
            }

            @Override
            public void onNext(String s) {
                LogHelp.e("xiaoma","subscriber-onNext"+s+Thread.currentThread().getName());
            }
        };


        //被观察者
       Observable observable= Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                LogHelp.e("xiaoma","observable-call"+Thread.currentThread().getName());
                subscriber.onNext("Hello"+1/0);
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });
        
        String[] names={"你","好"};
        Observable observable1=Observable.from(names);
        Observable observable2 = Observable.just("去", "做", "吧");
        observable1.subscribe(subscriber);
        //如果观察者是subscriber则无法显示对应的内容，应该是没有取消订阅
        observable2.subscribe(observer);  
        
//        observable.subscribe(observer);
//        observable.subscribe(subscriber);
        
       Action0 onCompleteAction= new Action0(){

            @Override
            public void call() {
                LogHelp.e("xiaoma","action0-onCompleteAction");
            }
        };
        
       Action1 onNextAction= new Action1<String>(){

            @Override
            public void call(String s) {
                LogHelp.e("xiaoma","action1-onNextAction"+s);
            }
        };
        
       Action1 onErrorAction= new Action1<Throwable>(){

            @Override
            public void call(Throwable throwable) {
                LogHelp.e("xiaoma","action1-onErrorAction"+throwable.getMessage()); 
            }
        };
        
        //如果使用这个，如何发生错误，就无法捕获到错误，页面崩溃
//        observable.subscribe(onNextAction); 
//        observable.subscribe(onNextAction,onErrorAction);
//        observable.subscribe(onNextAction,onErrorAction,onCompleteAction);
        String[] name = {".","?"};
        Observable.from(name).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                LogHelp.e("xiaoma","整体-onNextAction"+s); 
            }
        });

        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        Log.d("xiaoma", "number:" + number);
                    }
                });
        
        
        observable.just("images/logo.jpg").
                map(new Func1<String, Bitmap>() {

                    @Override
                    public Bitmap call(String s) {
                        return BitmapFactory.decodeFile(s);
                    }
                }).subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                
            }
        });
     
    }
}
