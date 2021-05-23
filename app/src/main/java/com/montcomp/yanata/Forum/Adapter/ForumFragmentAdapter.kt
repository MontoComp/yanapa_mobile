package com.montcomp.yanata.Forum.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.montcomp.yanata.AddComment.MyViewBottomComment
import com.montcomp.yanata.Base.ComonDomain.ForumResponse
import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse
import com.montcomp.yanata.DonateProject.DonateProjectActivity
import com.montcomp.yanata.Forum.ForumFragment
import com.montcomp.yanata.Projects.ProjectsFragment
import com.montcomp.yanata.R
import java.util.*



class ForumFragmentAdapter(val contxt1: ForumFragment, val lista:ArrayList<ForumResponse>) : RecyclerView.Adapter<ForumFragmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup?.context).inflate(R.layout.item_fg_comment, viewGroup, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return  lista.size;
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var castedActivityTest = contxt1

        var itemdata=lista[position]

        holder.title.text=itemdata.title

        holder.username.text= itemdata.name.subSequence(0,1).toString().capitalize()

        holder.name.text=itemdata.name+":"

        holder.description.text=itemdata.description

        holder.hour.text=itemdata.hour

        holder.date.text=itemdata.date
    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        val title: TextView = itemView.findViewById(R.id.tv_item_project_comment)
        val name: TextView = itemView.findViewById(R.id.tv_item_name_comment)
        val description: TextView = itemView.findViewById(R.id.tv_item_comment_comment)
        val username: TextView = itemView.findViewById(R.id.tv_user_name_project)
        val hour: TextView = itemView.findViewById(R.id.tv_hour_comment)
        val date: TextView = itemView.findViewById(R.id.tv_date_comment)


    }


}

