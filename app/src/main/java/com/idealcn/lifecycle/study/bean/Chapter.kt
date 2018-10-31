package com.idealcn.lifecycle.study.bean

import java.io.Serializable

/**
 * 公众号
 *
 */
class Chapter(var children : List<ChapterChild>,
              var courseId : Int,
              var id :Int,
              var name :String,
              var order : Long,
              var parentChapterId : Int,
              var userControlSetTop : Boolean,
              var visible :Int) : Serializable {

    inner class ChapterChild{

    }
}

/*
 "children": [],
            "courseId": 13,
            "id": 415,
            "name": "谷歌开发者",
            "order": 190006,
            "parentChapterId": 407,
            "userControlSetTop": false,
            "visible": 1
 */