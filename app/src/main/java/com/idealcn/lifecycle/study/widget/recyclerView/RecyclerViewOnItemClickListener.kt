package com.idealcn.lifecycle.study.widget.recyclerView

import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

/**
 * @author: guoning
 * @date: 2018/12/11 11:22
 * @description: RecyclerView的item的单击和长按
 */
abstract class RecyclerViewOnItemClickListener constructor(var recyclerView: RecyclerView)
    : RecyclerView.OnItemTouchListener {

    private lateinit var gestureDetectorCompat: GestureDetectorCompat
    private lateinit var simpleOnGestureListener: GestureDetector.OnGestureListener
    init {
        simpleOnGestureListener = object : GestureDetector.SimpleOnGestureListener(){
            override fun onSingleTapUp(e: MotionEvent?): Boolean {
               e?.let {
                   val under : View? = recyclerView.findChildViewUnder(e.x, e.y)
                   under?.let {
                       val holder = recyclerView.getChildViewHolder(under)
                       onItemClick(holder)
                       holder
                   }
                   under
               }
                return super.onSingleTapUp(e)
            }

            override fun onLongPress(e: MotionEvent?) {
                super.onLongPress(e)
                e?.let {
                    val under : View? = recyclerView.findChildViewUnder(e.x, e.y)
                    under?.let {
                        val holder = recyclerView.getChildViewHolder(under)
                        onItemLongClick(holder)
                        holder
                    }
                    under
                }
            }


        }
        gestureDetectorCompat =    GestureDetectorCompat(recyclerView.context,simpleOnGestureListener)
    }

    abstract fun onItemClick(holder: RecyclerView.ViewHolder?)
    abstract fun onItemLongClick(holder: RecyclerView.ViewHolder?)


    override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {
        e?.let {
            gestureDetectorCompat.onTouchEvent(e)
        }
    }

    override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
        e?.let {
           return gestureDetectorCompat.onTouchEvent(e)
        }
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }
}