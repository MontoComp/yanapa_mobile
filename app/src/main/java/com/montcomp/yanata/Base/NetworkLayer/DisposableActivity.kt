package com.montcomp.yanata.Base.NetworkLayer

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle

import android.util.Base64
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.montcomp.yanata.Base.ComonDomain.GlobalApplication
import com.montcomp.yanata.R
import java.io.*
import java.nio.file.Files


open class DisposableActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         GlobalApplication.appContext = this
         val toolbar = findViewById<View>(R.id.common_toolbar) as? Toolbar
         setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_mail -> {

            true
        }
        R.id.action_notifications -> {

            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    fun displayWaiting() {
        val progressBarHolder = findViewById(R.id.common_waiting_container) as? RelativeLayout
        val inAnimation = AlphaAnimation(0f, 1f)
        progressBarHolder?.animation = inAnimation
        progressBarHolder?.visibility = View.VISIBLE

    }

    fun displayWaitingLogin() {
        displayWaiting()
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    /*fun isPatient():Boolean{
        return (Keys.UserData.PROFILE.value.toLowerCase().equals(Constants.PersonType.patients.type.toLowerCase()))
    }*/
    fun clearFlag(){
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    fun hideWaiting() {
        val progressBarHolder = findViewById<View>(R.id.common_waiting_container) as? RelativeLayout
        val outAnimation = AlphaAnimation(1f, 0f)
        progressBarHolder?.animation = outAnimation
        progressBarHolder?.visibility = View.GONE
    }


    fun enableToolbarHomeButton() {
        getSupportActionBar()?.setHomeButtonEnabled(true)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    }

    fun imageToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }

    fun pdfToBase64(file: File):String{
        var byteArray:ByteArray?=null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            byteArray=Files.readAllBytes(file.toPath())
            //file.readBytes()
        } else {
           // file.readBytes()
            try {
                byteArray=read(file)
            }catch (e: Throwable){
                Log.e("Error", e.message!!)
            }
            //Toast.makeText(this, "You need update your software version", Toast.LENGTH_SHORT).show()
        }
        return Base64.encodeToString(byteArray as ByteArray?, Base64.NO_WRAP)
    }

    @Throws(IOException::class)
    open fun read(file: File): ByteArray? {
        var ous: ByteArrayOutputStream? = null
        var ios: InputStream? = null
        try {
            val buffer = ByteArray(4096)
            ous = ByteArrayOutputStream()
            ios = FileInputStream(file)
            var read = 0
            while (ios.read(buffer).also({ read = it }) != -1) {
                ous.write(buffer, 0, read)
            }
        } finally {
            try {
                ous?.close()
            } catch (e: IOException) {
            }
            try {
                if (ios != null) ios.close()
            } catch (e: IOException) {
            }
        }
        return ous!!.toByteArray()
    }

    fun  deletePreferences(){

        val preferences = getSharedPreferences("calendar", 0)
        preferences.edit().remove("month").commit()
    }

    fun finishActivity(){
        finish()
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}