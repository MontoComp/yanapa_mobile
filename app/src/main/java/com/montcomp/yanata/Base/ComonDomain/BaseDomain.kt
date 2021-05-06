package com.montcomp.yanata.Base.ComonDomain

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.app.Application


open class BaseDomain {
    val resultCode: String? = null

    fun validate(block: () -> Unit) {
        if(resultCode == "WRC_0003") {
            val context = GlobalApplication.appContext ?: return

            val builder = AlertDialog.Builder(context)
            builder.setMessage("Session Timeout")
            builder.setPositiveButton("Ok") { dialog, which ->
                /*PersistentData.instance.loginSession.value=false
                val loginActivity = Intent(context, LoginActivity::class.java)
                startActivity(context, loginActivity, null)
                dialog.dismiss()
                (context as? DisposableActivity)?.finish()*/
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        } else {
            block()
        }
    }
}

open class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        var appContext: Context? = null
            set
    }
}