package com.idealcn.lifecycle.study.bean

class ChapterHistory(
    var curPage: Int, var datas: ArrayList<ChapterHistoryChild>, var offset: Int, var over: Boolean, var pageCount: Int
    , var size: Int, var total: Int
) {
    /*
    "curPage": 1,
        "datas": [],
        "offset": 0,
        "over": true,
        "pageCount": 0,
        "size": 20,
        "total": 0
     */

    inner class ChapterHistoryChild(var apkLink : String,
                                   var author : String,
                                    var chapterId :Int,
                                   var chapterName : String,
                                   var collect : Boolean,
                                    var courseId : Int,
                                    var desc : String,
                                    var envelopePic : String,
                                    var fresh :Boolean,
                                    var id : Int,
                                    var link : String,
                                    var niceDate : String,
                                    var origin : String,
                                    var projectLink : String,
                                    var publishTime : Long,
                                    var superChapterId : Int,
                                    var superChapterName : String,
                                    var title : String,
                                    var type : Int,
                                    var userId :Int,
                                    var visible :Int,
                                    var zan : Int,
                                    var tags :ArrayList<Tag>)

    inner class Tag(var name : String,var url : String)

    /*
    "apkLink": "",
                "author": "郭霖",
                "chapterId": 409,
                "chapterName": "郭霖",
                "collect": false,
                "courseId": 13,
                "desc": "",
                "envelopePic": "",
                "fresh": false,
                "id": 7431,
                "link": "https://mp.weixin.qq.com/s/BB7RpFnqAJ-vj-cq645LpA",
                "niceDate": "1天前",
                "origin": "",
                "projectLink": "",
                "publishTime": 1540742400000,
                "superChapterId": 408,
                "superChapterName": "公众号",
                "tags": [
                    {
                        "name": "公众号",
                        "url": "/wxarticle/list/409/1"
                    }
                ],
                "title": "你以为你已经很了解Android Studio了？",
                "type": 0,
                "userId": -1,
                "visible": 1,
                "zan": 0
     */
}