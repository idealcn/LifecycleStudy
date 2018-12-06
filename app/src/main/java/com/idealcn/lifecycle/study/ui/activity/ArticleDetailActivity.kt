package com.idealcn.lifecycle.study.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.webkit.WebView
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.bean.Article

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

       val toolbar =  findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolbar)
        with(toolbar){
           title = article. title
        }

        val webView = findViewById<WebView>(R.id.webView)
        webView.loadUrl(article.link)


    }


}
