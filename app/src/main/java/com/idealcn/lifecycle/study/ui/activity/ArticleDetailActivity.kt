package com.idealcn.lifecycle.study.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.bean.Article
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : AppCompatActivity() {

    private lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        article = intent.getSerializableExtra("article") as Article

        //        ActionBar actionBar = getSupportActionBar();
        //        actionBar.setDisplayShowTitleEnabled(true);
        //        actionBar.setDisplayShowCustomEnabled(true);
        //        actionBar.setTitle(article.getTitle());


        setSupportActionBar(toolBar)
        with(toolBar){
           title = article. title
        }

        webView.loadUrl(article.link)


    }


}
