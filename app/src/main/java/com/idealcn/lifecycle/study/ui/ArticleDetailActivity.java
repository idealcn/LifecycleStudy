package com.idealcn.lifecycle.study.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.idealcn.lifecycle.study.R;
import com.idealcn.lifecycle.study.bean.Article;
import com.idealcn.lifecycle.study.bean.BaseResponseBean;
import com.idealcn.lifecycle.study.http.RetrofitClient;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Timed;

import java.util.concurrent.TimeUnit;

public class ArticleDetailActivity extends AppCompatActivity {

    private Article article;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        article = (Article) getIntent().getExtras().getSerializable("article");

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowTitleEnabled(true);
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setTitle(article.getTitle());



    }

    public void collect(View view) {

        Disposable hello = Observable.just("hello")
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        System.out.println("----------doOnSubscribe11111---------");
                    }
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("----------doOnError11111---------");

                    }
                }).doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("----------doFinally11111---------");

                    }
                }).flatMap(new Function<String, ObservableSource<int[]>>() {
                    @Override
                    public ObservableSource<int[]> apply(String s) throws Exception {
                        return Observable.just(new int[60]);
                    }
                })

                .timeInterval(TimeUnit.MILLISECONDS)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        System.out.println("----------doOnSubscribe22222---------");

                    }
                }).subscribe(new Consumer<Timed<int[]>>() {
                    @Override
                    public void accept(Timed<int[]> timed) throws Exception {
                        long time = timed.time();
                        System.out.println("time: "+time);
                    }
                });


//        Disposable disposable = RetrofitClient.newInstance().getApi().collect(article.getId())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<BaseResponseBean<Boolean>>() {
//                    @Override
//                    public void accept(BaseResponseBean<Boolean> booleanBaseResponseBean) throws Exception {
//
//                    }
//                });
    }
}
