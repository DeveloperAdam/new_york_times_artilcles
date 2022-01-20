package com.noor.nytimes.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.noor.nytimes.R
import com.noor.nytimes.data.NyTimesData
import com.noor.nytimes.data.Record

/**
 * Created by Adam Noor on 20/01/2022.
 */
class NyTimesArticlesAdapter(nyTimesData: NyTimesData, context: Context) :
    RecyclerView.Adapter<NyTimesArticlesAdapter.ViewHolder>() {

    val data = nyTimesData
    var mContext = context
    var onItemClick: OnItemClickListener? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var layoutItem = view.findViewById(R.id.layoutItem) as RelativeLayout
        var ivNewsImage = view.findViewById(R.id.ivImage) as ImageView
        var tvNewsTitle = view.findViewById(R.id.tvNewsTitle) as TextView
        var tvAuthor = view.findViewById(R.id.tvAuthor) as TextView
        var tvDate = view.findViewById(R.id.tvDate) as TextView
        var tvSource = view.findViewById(R.id.tvSource) as TextView

        fun bind(record: Record) {

            tvNewsTitle.text = record.title
            tvAuthor.text = record.byline
            tvDate.text = record.publishedDate
            tvSource.text = record.source

            try {
                record.media[0].mediaMetadata[2].url.let {
                    Glide.with(mContext).load(it).placeholder(R.drawable.ic_launcher_background)
                        .into(ivNewsImage)
                }
            } catch (e: Exception) {
                Log.d("url error", e.message.toString())
            }
            layoutItem.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemClick?.onItemClick(data.resultData[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = data.resultData[position]
        holder.bind(record = item)

    }

    override fun getItemCount(): Int {
        return data.resultData.size
    }

    interface OnItemClickListener {
        fun onItemClick(item: Record)
    }

}