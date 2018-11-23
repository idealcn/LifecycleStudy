package com.idealcn.lifecycle.study.transformer

/**
 * @author: guoning
 * @date: 2018/11/14 18:09
 * @description:
 */
import java.lang.Exception

open class CustomException : Exception()

class ConnectFailedAlertDialogException : CustomException()

class TokenExpiredException : CustomException()