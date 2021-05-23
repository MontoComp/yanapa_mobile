package com.montcomp.yanata.People.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.montcomp.yanata.AddComment.MyViewBottomComment
import com.montcomp.yanata.Base.ComonDomain.ForumResponse
import com.montcomp.yanata.Base.ComonDomain.PeopleResponse
import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse
import com.montcomp.yanata.DonateProject.DonateProjectActivity
import com.montcomp.yanata.Forum.ForumFragment
import com.montcomp.yanata.People.PeopleFragment
import com.montcomp.yanata.Projects.ProjectsFragment
import com.montcomp.yanata.R
import java.util.*



class PeopleFragmentAdapter(val contxt1: PeopleFragment, val lista:ArrayList<PeopleResponse>) : RecyclerView.Adapter<PeopleFragmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup?.context).inflate(R.layout.item_fg_people, viewGroup, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return  lista.size;
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var castedActivityTest = contxt1

        var itemdata=lista[position]

        holder.distric.text=itemdata.district
        holder.stage.text=itemdata.stage
        holder.male.text="Hombres: "+itemdata.male.toString()
        holder.female.text="Mujeres: "+itemdata.female.toString()
        holder.total.text="Total: "+itemdata.total.toString()

    }

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        val distric: TextView = itemView.findViewById(R.id.tv_item_total_district)
        val stage: TextView = itemView.findViewById(R.id.tv_item_stage)
        val male: TextView = itemView.findViewById(R.id.tv_item_total_male)
        val female: TextView = itemView.findViewById(R.id.tv_item_total_female)
        val total: TextView = itemView.findViewById(R.id.tv_item_total_people)
    }


}

