package com.idealcn.lifecycle.study.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import java.util.logging.Logger

/**
 * @author: guoning
 * @date: 2018/11/30 11:18
 * @description:
 */
abstract class BaseActivity : AppCompatActivity() {

    private  val logger: Logger = Logger.getLogger("BaseActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        logger.info(this.javaClass.simpleName+"-------onCreate")
    }

    override fun onStart() {
        super.onStart()
        logger.info(this.javaClass.simpleName+"-------onStart")
    }

    override fun onResume() {
        super.onResume()
        logger.info(this.javaClass.simpleName+"-------onResume")
    }

    override fun onPause() {
        super.onPause()
        logger.info(this.javaClass.simpleName+"-------onPause")
    }

    override fun onStop() {
        super.onStop()
        logger.info(this.javaClass.simpleName+"-------onStop")
    }

    override fun onRestart() {
        super.onRestart()
        logger.info(this.javaClass.simpleName+"-------onRestart")

    }

    override fun onDestroy() {
        super.onDestroy()
        logger.info(this.javaClass.simpleName+"-------onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        logger.info(this.javaClass.simpleName+"-------onSaveInstanceState")
        outState?.let {
            outState.putString("state","我是保存的状态")
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        logger.info(this.javaClass.simpleName+"-------onRestoreInstanceState,"+savedInstanceState?.let {
            val state = it.getString("state")
            state
        })

    }

    abstract fun getLayout(): Int


    protected fun toast(msg :String) = Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()

}