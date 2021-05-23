package com.montcomp.yanata.DetailProject

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Transformations.map
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse
import com.montcomp.yanata.DonateProject.DonateProjectActivity
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

class DetailProjectActivity : AppCompatActivity() {
    var mynum:String ="961524691"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_project)

        checkPermission()



        val toolbarTitle = this.findViewById(R.id.toolbar_title_back)  as TextView?
        toolbarTitle?.setText("Información del proyecto")
        toolbarTitle?.setTextColor(this.resources.getColor(R.color.white))

        var rec_objproject = intent.getStringExtra("myobjproject")?:null

        if (rec_objproject!=null){

            var obj_project : ProjectsResponse = Gson().fromJson(rec_objproject,ProjectsResponse::class.java)

            findViewById<TextView>(R.id.tv_name_project).text = obj_project.title

            findViewById<TextView>(R.id.tv_manager_project).text = obj_project.manager.subSequence(0,1).toString().capitalize()

            findViewById<TextView>(R.id.tv_name_user_project).text =  obj_project.manager
            findViewById<TextView>(R.id.tv_dni_project).text =obj_project.dni.toString()
            findViewById<TextView>(R.id.tv_address_project).text =obj_project.address
            findViewById<TextView>(R.id.tv_mail_project).text =obj_project.email
            findViewById<TextView>(R.id.tv_phone_project).text =  obj_project.phone.toString()

            mynum=obj_project.phone.toString()

            /*findViewById<TextView>(R.id.tv_name_user_project).text = Html.fromHtml("<font color=${resources.getColor(R.color.dom_yanapa)}>Encargado: </font>"+ obj_project.manager)
            findViewById<TextView>(R.id.tv_dni_project).text =Html.fromHtml("<font color=${resources.getColor(R.color.dom_yanapa)}>Dni: </font>"+ obj_project.dni.toString())
            findViewById<TextView>(R.id.tv_address_project).text =Html.fromHtml("<font color=${resources.getColor(R.color.dom_yanapa)}>Dirección: </font>"+ obj_project.address)
            findViewById<TextView>(R.id.tv_mail_project).text =Html.fromHtml("<font color=${resources.getColor(R.color.dom_yanapa)}>Correo electrónico: </font>"+ obj_project.email)
            findViewById<TextView>(R.id.tv_phone_project).text = Html.fromHtml("<font color=${resources.getColor(R.color.dom_yanapa)}>Celular: </font>"+ obj_project.phone.toString())*/

        }

        findViewById<ImageView>(R.id.imageView).setOnClickListener {
            callPhone(mynum)
        }


        findViewById<FloatingActionButton>(R.id.fab_donate).setOnClickListener {
            val intent = Intent(this,DonateProjectActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            onBackPressed()
        }

    }

    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CALL_PHONE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CALL_PHONE),
                        42)
            }
        } else {
            // Permission has already been granted
            //callPhone(mynum)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 42) {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // permission was granted, yay!
                //callPhone(mynum)
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()
            } else {
                // permission denied, boo! Disable the
                // functionality
                Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show()
            }
            return
        }
    }

    fun callPhone(num:String){
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "${num}"))
        startActivity(intent)
    }





}