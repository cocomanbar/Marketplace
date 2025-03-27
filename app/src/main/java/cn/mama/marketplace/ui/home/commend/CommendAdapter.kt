package cn.mama.marketplace.ui.home.commend

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.BaseAdapter
import cn.mama.marketplace.R

class CommendAdapter(private val context: Context, private val list: Array<CommendDataItem>) :
    BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(
        position: Int,
        convertView: android.view.View?,
        parent: android.view.ViewGroup?
    ): android.view.View {
        val view = LayoutInflater.from(context).inflate(
            R.layout.layout_commend_item,
            parent,
            false
        )
        val tvTitle = view.findViewById<android.widget.TextView>(cn.mama.marketplace.R.id.tv_title)
        val tvConnect =
            view.findViewById<android.widget.TextView>(cn.mama.marketplace.R.id.tv_content)
        val data = list[position]
        tvTitle.text = data.title
        if (data.content?.isEmpty() == true) {
            tvConnect.visibility = android.view.View.GONE
        } else {
            tvConnect.text = data.content
            tvConnect.visibility = android.view.View.VISIBLE
        }
        return view
    }
}