package com.montcomp.yanata.DonateProject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Transformations.map
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.montcomp.yanata.Forum.ForumFragment
import com.montcomp.yanata.Home.HomeFragment
import com.montcomp.yanata.Login.LoginActivity
import com.montcomp.yanata.People.PeopleFragment
import com.montcomp.yanata.Projects.ProjectsFragment
import com.montcomp.yanata.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DonateProjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donateproject)

        val toolbarTitle = this.findViewById(R.id.toolbar_title_back)  as TextView?
        toolbarTitle?.setText("Donación")
        toolbarTitle?.setTextColor(this.resources.getColor(R.color.white))

        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            onBackPressed()
        }

    }


}