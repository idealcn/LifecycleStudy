package com.idealcn.lifecycle.study.bean

import java.io.Serializable

data class Article(var apkLink:String,
                   var author :String,
                   var chapterId : Int,
                   var chapterName : String,
                   var collect : Boolean,
                   var courseId: Int,
                   var desc : String,
                   var envelopePic : String,
                   var fresh : Boolean,
                   var id : Int,
                    var link : String,
                   var title:String) : Serializable
    /*
    "apkLink": "",
                "author": "ManbangGroup",
                "chapterId": 385,
                "chapterName": "架构",
                "collect": false,
                "courseId": 13,
                "desc": "Phantom &mdash; 唯一零 Hook 稳定占坑类 Android 热更新插件化方案\r\n",
                "envelopePic": "http://www.wanandroid.com/resources/image/pc/default_project_img.jpg",
                "fresh": true,
                "id": 7396,
                "link": "http://www.wanandroid.com/blog/show/2405",
                "niceDate": "14小时前",
                "origin": "",
                "projectLink": "https://github.com/ManbangGroup/Phantom",
                "publishTime": 1540396116000,
                "superChapterId": 294,
                "superChapterName": "开源项目主Tab",
                "tags": [
                    {
                        "name": "项目",
                        "url": "/project/list/1?cid=385"
                    }
                ],
                "title": "Phantom &mdash; 唯一零 Hook 稳定占坑类 Android 热更新插件化方案",
                "type": 0,
                "userId": -1,
                "visible": 1,
                "zan": 0
     */
