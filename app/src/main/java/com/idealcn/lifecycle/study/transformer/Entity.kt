package com.idealcn.lifecycle.study.transformer

/**
 * @author: guoning
 * @date: 2018/11/14 17:53
 * @description:
 */

open class BaseEntity {
    var statusCode: Int = 0
    var message: String = ""
}

data class UserInfo(val username: String = "",
                    val age: Int = 0) : BaseEntity()