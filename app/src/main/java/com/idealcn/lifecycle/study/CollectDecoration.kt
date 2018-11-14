package com.idealcn.lifecycle.study

import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.idealcn.lifecycle.study.bean.Article
import java.util.logging.Logger

/**
 * 用于绘制收藏标记
 */
class CollectDecoration( context: Context) : RecyclerView.ItemDecoration() {


    private val path : Path = Path()
    private val textPath : Path = Path()

    private val pathPaint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val list = arrayListOf<Article>()

    private val logger : Logger = Logger.getLogger("CollectDecoration")


    init {
        pathPaint.color = ContextCompat.getColor(context,R.color.colorAccent)
        pathPaint.style = Paint.Style.FILL
        pathPaint.strokeWidth = 1f


        textPaint.strokeWidth = 3f
        textPaint.color = Color.WHITE
        textPaint.style = Paint.Style.STROKE
        textPaint.textSize = 14f
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        super.onDrawOver(canvas, parent, state)
        val size = list.size
        if (size<=0) return

        val text  = "已 收 藏"
        val layoutManager = parent.layoutManager as LinearLayoutManager
        /**
         * Return the current number of child views attached to the parent RecyclerView.
         * This does not include child views that were temporarily detached and/or scrapped.
         *
         * @return Number of attached children 这个值是显示在屏幕上的child的数量
         */
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        logger.info("firstVisibleItemPosition: $firstVisibleItemPosition")
        val childCount = layoutManager.childCount
        for (x in 0 until  childCount){

            val child = layoutManager.getChildAt(x)
            val childAdapterPosition = parent.getChildAdapterPosition(child)
            val childLayoutPosition = parent.getChildLayoutPosition(child)
            val article : Article? = list[childLayoutPosition]
            if (null==article || !article.collect)continue

            logger.info("childAdapterPosition: $childAdapterPosition,   childLayoutPosition: $childLayoutPosition")
            val top = child.top
           // logger.info("childCount: $x, top : $top")
            val layoutParams = child.layoutParams as RecyclerView.LayoutParams
            path.moveTo((parent.width- layoutParams.rightMargin-180).toFloat(), top.toFloat())
            path.lineTo((parent.width- layoutParams.rightMargin-70).toFloat(),top.toFloat())
            path.lineTo(parent.width.toFloat()- layoutParams.rightMargin,(top +70).toFloat())
            path.lineTo(parent.width.toFloat()- layoutParams.rightMargin, (top +180).toFloat())
            path.lineTo((parent.width- layoutParams.rightMargin-180).toFloat(), top.toFloat())
            canvas.drawPath(path,pathPaint)
            path.reset()

            textPath.moveTo((parent.width - layoutParams.rightMargin - 130).toFloat(),top.toFloat())
            textPath.lineTo((parent.width - layoutParams.rightMargin).toFloat(), (top+130).toFloat())
            canvas.drawTextOnPath(text,textPath, (child.height/2 -70).toFloat(),0f,textPaint)
            textPath.reset()
        }
    }

    fun setData(index:Int,list: ArrayList<Article>) {
        this.list.addAll(index,list)
    }


    fun  getData() : ArrayList<Article>{
        return list
    }


}