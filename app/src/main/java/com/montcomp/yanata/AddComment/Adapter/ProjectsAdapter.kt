package com.montcomp.yanata.AddComment.Adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse
import com.montcomp.yanata.R

class ProjectsAdapter(val context: Context, var listEntity: ArrayList <ProjectsResponse>):BaseAdapter() {

    val mInflater: LayoutInflater = LayoutInflater.from(context)

    init {
        Log.v("AdaptadorEntity", listEntity.toString())
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: ItemRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.item_projects, parent, false)
            vh = ItemRowHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemRowHolder
        }

        // setting adapter item height programatically.

        //val params = view.layoutParams
       // params.height = 60
       // view.layoutParams = params

        vh.label.text = listEntity[position].title
        vh.label.setTextColor(context.resources.getColor(R.color.black))

        return view
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long { return 0 }

    override fun getCount(): Int = listEntity.size


    private class ItemRowHolder(row: View?) {
        val label: TextView
        init {
            this.label = row?.findViewById(R.id.txtnameprojects) as TextView
        }
    }
}