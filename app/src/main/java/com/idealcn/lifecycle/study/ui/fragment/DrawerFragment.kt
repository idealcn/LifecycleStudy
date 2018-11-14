package com.idealcn.lifecycle.study.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.idealcn.lifecycle.study.R
import com.idealcn.lifecycle.study.ui.activity.RegisterActivity
import kotlinx.android.synthetic.main.fragment_drawer.*

class DrawerFragment :Fragment() {

    private lateinit var _context :Context

   companion object {
       fun newInstance() : DrawerFragment{
            return DrawerFragment()
       }
   }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this._context = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  inflater.inflate(R.layout.fragment_drawer,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        avatar.setOnClickListener {
            startActivity(Intent(_context,RegisterActivity::class.java))
        }
    }

}
