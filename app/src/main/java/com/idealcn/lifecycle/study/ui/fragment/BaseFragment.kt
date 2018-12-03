package com.idealcn.lifecycle.study.ui.fragment

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.ui.mvp.contract.BaseContract
import io.reactivex.disposables.CompositeDisposable
import java.util.logging.Logger

abstract  class BaseFragment<V : BaseContract.BaseView> : Fragment() {

    protected val logger : Logger = Logger.getLogger("BaseFragment")

    protected lateinit var _context : Context

    protected val compositeDisposable = CompositeDisposable()

    protected var isViewCreated = false



    override fun onAttach(context: Context) {
        super.onAttach(context)
        logger.info(this.javaClass.simpleName+"--------onAttach--------")
        _context = context
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        logger.info(this.javaClass.simpleName+"--------setUserVisibleHint--------$isVisibleToUser")
        if (isVisibleToUser && isViewCreated) {
            loadData()
        }
    }

    abstract fun loadData()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logger.info(this.javaClass.simpleName+"--------onCreateView--------")

        return inflater.inflate(getLayout(),container,false)
    }

    abstract fun getLayout(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logger.info(this.javaClass.simpleName+"--------onViewCreated--------")
        isViewCreated = true
        userVisibleHint = true
       // init()
        initViews()
    }

    /**
     * 做一些必要的初始化操作
     */
    abstract fun init()

    abstract fun initViews()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logger.info(this.javaClass.simpleName+"--------onActivityCreated--------")

    }

    override fun onStart() {
        super.onStart()
        logger.info(this.javaClass.simpleName+"--------onStart--------")

    }

    override fun onResume() {
        super.onResume()
        logger.info(this.javaClass.simpleName+"--------onResume--------")

    }

    override fun onPause() {
        super.onPause()
        logger.info(this.javaClass.simpleName+"--------onPause--------")

    }

    override fun onStop() {
        super.onStop()
        logger.info(this.javaClass.simpleName+"--------onStop--------")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        logger.info(this.javaClass.simpleName+"--------onDestroyView--------")
        userVisibleHint = false
        isViewCreated = false
        compositeDisposable.dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.info(this.javaClass.simpleName+"--------onDestroy--------")

    }

    override fun onDetach() {
        super.onDetach()
        logger.info(this.javaClass.simpleName+"--------onDetach--------")

    }



    fun toast(msg : String) = Toast.makeText(_context,msg,Toast.LENGTH_SHORT).show()


    fun showRequestDialog() = AlertDialog.Builder(_context)
        .setView(R.layout.dialog_request)
//        .setNegativeButton("取消") { dialog, which -> dialog?.dismiss() }
        .show()
}