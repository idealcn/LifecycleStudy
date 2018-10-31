package com.idealcn.lifecycle.study.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.logging.Logger

abstract  class BaseFragment : Fragment() {

    protected val logger : Logger = Logger.getLogger("BaseFragment");

    protected lateinit var _context : Context

    protected var isViewCreated = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        logger.info("--------onAttach--------")
        _context = context
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        logger.info("--------setUserVisibleHint--------")
        if (isVisibleToUser && isViewCreated) {
            loadData()
        }
    }

    abstract fun loadData()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logger.info("--------onCreateView--------")

        return inflater.inflate(getLayout(),container,false)
    }

    abstract fun getLayout(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logger.info("--------onViewCreated--------")
        isViewCreated = true
        userVisibleHint = true
        initViews()
    }

    abstract fun initViews()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logger.info("--------onActivityCreated--------")

    }

    override fun onStart() {
        super.onStart()
        logger.info("--------onStart--------")

    }

    override fun onResume() {
        super.onResume()
        logger.info("--------onResume--------")

    }

    override fun onPause() {
        super.onPause()
        logger.info("--------onPause--------")

    }

    override fun onStop() {
        super.onStop()
        logger.info("--------onStop--------")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        logger.info("--------onDestroyView--------")
        userVisibleHint = false
        isViewCreated = false
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.info("--------onDestroy--------")

    }

    override fun onDetach() {
        super.onDetach()
        logger.info("--------onDetach--------")

    }


}