package com.montcomp.yanata

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Transformations.map
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.montcomp.yanata.Forum.ForumFragment
import com.montcomp.yanata.Home.HomeFragment
import com.montcomp.yanata.Login.LoginActivity
import com.montcomp.yanata.People.PeopleFragment
import com.montcomp.yanata.Projects.ProjectsFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var rec_id_main = intent.getIntExtra("id_main",0)


        var nav_bottom = this.findViewById<ChipNavigationBar>(R.id.bottom_nav)

        nav_bottom.setOnItemSelectedListener {
                MenuItem ->
            when (MenuItem) {
                R.id.home -> {
                    loadFragmentHome()
                    true
                }
                R.id.people -> {
                    loadFragmentPeople()
                    true
                }
                R.id.projects -> {
                    loadFragmentProjects()
                    true
                }
                R.id.forum -> {
                    loadFragmentForum()
                    true
                }
                else -> false
            }
        }

        when(rec_id_main){
            1->{
                nav_bottom.setItemSelected(R.id.home)
            }
            2->{
                nav_bottom.setItemSelected(R.id.people)
            }
            3->{
                nav_bottom.setItemSelected(R.id.projects)
            }
            4->{
                nav_bottom.setItemSelected(R.id.forum)
            }
            else->{
                nav_bottom.setItemSelected(R.id.home)
            }
        }

        //nav_bottom.setItemSelected(R.id.home)
        //nav_bottom.showBadge(R.id.favorites,5)
        //nav_bottom.showBadge(R.id.favorites)
    }

    fun loadFragmentHome() {

        var fragment = HomeFragment()
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.main_id, fragment)
            .commit()
    }
    fun loadFragmentPeople() {

        var fragment = PeopleFragment()
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.main_id, fragment)
            .commit()
    }

    fun loadFragmentProjects() {

        var fragment = ProjectsFragment()
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.main_id, fragment)
                .commit()
    }

    fun loadFragmentForum() {

        var fragment = ForumFragment()
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
                .replace(R.id.main_id, fragment)
                .commit()
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setMessage("Salir de la app")
        builder.setPositiveButton("SI") { dialog, which ->
            /*PersistentData.instance.loginSession.value=false
            val loginActivity = Intent(context, LoginActivity::class.java)
            startActivity(context, loginActivity, null)
            dialog.dismiss()
            (context as? DisposableActivity)?.finish()*/
            System.exit(0)
        }
        builder.setNegativeButton("NO") { dialog, which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}