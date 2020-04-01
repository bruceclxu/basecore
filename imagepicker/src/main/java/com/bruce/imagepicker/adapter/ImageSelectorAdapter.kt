package com.bruce.imagepicker.adapter

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bruce.imagepicker.R
import com.bruce.imagepicker.data.ImageModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.item_selector.view.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class ImageSelectorAdapter : RecyclerView.Adapter<ImageSelectorAdapter.Holder>() {
    private var mData: List<ImageModel>? = ArrayList()
    private var listener: ItemClickListener? = null
    private var haveSelected = 0
    private var selectList: ArrayList<ImageModel>? = ArrayList() // 选中的图片一次顺序放入
        get() {
            return field
        }

    var data: List<ImageModel>?
        get() = mData
        set(data) {
            mData = data
            notifyDataSetChanged()
        }

    interface ItemClickListener {
        fun onItemClick(position: Int)

    }

    fun setListener(listener: ItemClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_selector, parent, false)
        return Holder(itemView)
    }

    override fun getItemCount(): Int {
        if (mData == null) return 0
        return mData!!.size
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(holder.itemView.context)
                .load(data!![position].path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.itemView.image)
        if (data!![position].duration == 0) {
            holder.itemView.tv_duration.visibility = View.GONE
        } else {
            holder.itemView.tv_duration.visibility = View.VISIBLE
            holder.itemView.tv_duration.text = SimpleDateFormat("mm:ss").format(data!![position].duration)
        }

        if (data!![position].selectPosition > 0) {
            holder.itemView.tv_index.setBackgroundResource(R.drawable.bg_select_circle)
            holder.itemView.tv_index.text = data!![position].selectPosition.toString()
        } else {
            holder.itemView.tv_index.setBackgroundResource(R.drawable.bg_circle)
            holder.itemView.tv_index.text = ""
        }

        holder.itemView.clicks()
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe {
                    itemClick(position)
                    holder.itemView.tv_index.clearAnimation()
                    alpahAnimation(holder.itemView.tv_index)
                }

    }

    @Synchronized
    private fun itemClick(position: Int) {
        listener?.onItemClick(position)
        data!![position].isSelected = !data!![position].isSelected
        if (data!![position].isSelected) {
            haveSelected++
            data!![position].selectPosition = haveSelected
            selectList?.add(data!![position])
            notifyItemChanged(position)
        } else {
            haveSelected--
            updateIndex(data!![position].selectPosition)
            data!![position].selectPosition = -1
            selectList?.remove(data!![position])
            notifyItemChanged(position)
        }
    }

    private fun updateIndex(unSelectedIndex: Int) {
        for ((index, image) in this.mData!!.withIndex()) {
            if (image.isSelected && image.selectPosition > unSelectedIndex) {
                image.selectPosition--
                notifyItemChanged(index)
            }
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)


    private fun alpahAnimation(view: View) {
        val anim: ObjectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f)
        anim.duration = 500
        anim.start()
    }

}
