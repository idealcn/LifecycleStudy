package com.idealcn.lifecycle.study.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.idealcn.lifecycle.study.R;
import com.idealcn.lifecycle.study.bean.Article;
import com.idealcn.lifecycle.study.bean.BaseResponseBean;
import com.idealcn.lifecycle.study.http.RetrofitClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.Serializable;

public class ArticleDetailActivity extends AppCompatActivity {

    private         Article article;

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

        Disposable disposable = RetrofitClient.newInstance().getApi().collect(article.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponseBean<Boolean>>() {
                    @Override
                    public void accept(BaseResponseBean<Boolean> booleanBaseResponseBean) throws Exception {

                    }
                });
    }
}
