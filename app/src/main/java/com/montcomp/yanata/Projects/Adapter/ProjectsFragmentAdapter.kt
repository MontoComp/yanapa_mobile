package com.montcomp.yanata.Projects.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse
import com.montcomp.yanata.DetailProject.DetailProjectActivity
import com.montcomp.yanata.DonateProject.DonateProjectActivity
import com.montcomp.yanata.Projects.ProjectsFragment
import com.montcomp.yanata.R
import java.util.*



class ProjectsFragmentAdapter(val contxt1: ProjectsFragment, val lista:ArrayList<ProjectsResponse>) : RecyclerView.Adapter<ProjectsFragmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup?.context).inflate(R.layout.item_fg_project, viewGroup, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return  lista.size;
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var castedActivityTest = contxt1

        var itemdata=lista[position]

        holder.title.text=itemdata.title
        holder.manager.text=itemdata.manager

        holder.email.text=itemdata.email

        holder.ly_donate.setOnClickListener {
            val intent = Intent(holder.itemView.context, DonateProjectActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }

        holder.ly_detail.setOnClickListener {
            var objproject:String = Gson().toJson(itemdata,ProjectsResponse::class.java)
            val intent = Intent(holder.itemView.context, DetailProjectActivity::class.java)
            intent.putExtra("myobjproject",objproject)
            holder.itemView.context.startActivity(intent)
        }

    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        val title: TextView = itemView.findViewById(R.id.tv_item_title_project)
        val manager: TextView = itemView.findViewById(R.id.tv_item_manager_project)
        val email: TextView = itemView.findViewById(R.id.tv_item_email_project)
        val ly_donate: LinearLayout = itemView.findViewById(R.id.ly_donate_project)
        val ly_detail: LinearLayout = itemView.findViewById(R.id.ly_detail_project)

    }


}

