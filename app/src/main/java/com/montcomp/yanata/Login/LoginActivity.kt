package com.montcomp.yanata.Login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import com.google.android.material.textfield.TextInputEditText
import com.hcr.hcr_android.Base.PersistentData.PersistentData
import com.montcomp.yanata.Base.ComonDomain.LoginResponseZentros
import com.montcomp.yanata.Base.NetworkLayer.DisposableActivity
import com.montcomp.yanata.R

class LoginActivity : DisposableActivity(), LoginPresenterToViewProtocol {

    override val context: Context = this
    override var presenter: LoginViewToPresenterProtocol? = null

    override fun showLogin(argument: LoginResponseZentros) {
        //Toast.makeText(this,"${PersistentData.instance.userToken.value}",Toast.LENGTH_SHORT).show()
        //Log.d("MYTOKEN","${PersistentData.instance.userToken.value}")
        //val intent = Intent(this,HomeActivity::class.java)
        //startActivity(intent)
        //finishActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        LoginRouter.configure(this)

        //var username = findViewById<EditText>(R.id.et_user).text
        //var password = findViewById<EditText>(R.id.et_password).text

        var input_user = findViewById<TextInputEditText>(R.id.et_login_username)
        var input_password = findViewById<TextInputEditText>(R.id.et_login_password)


        findViewById<Button>(R.id.btn_login).setOnClickListener {
            //presenter?.validateLogin(username.toString(),password.toString())
            /*if (username.toString()=="MARTA"&&password.toString()=="test"){
                presenter?.validateLogin()

            }else{
                var alert= AlertDialog.Builder(this)
                alert.setIcon(context.resources.getDrawable(R.drawable.ic_alert_2))
                alert.setTitle("Mensaje de error")

                alert.setMessage("Contraseña o usuario incorrectos")
                alert.setOnDismissListener {dialog ->
                    /*var intent = Intent(context, MyaddusersActivity()::class.java)
                    ContextCompat.startActivity(context, intent, null)*/
                    //onBackPressed()
                }
                alert.show()
            }*/

        }

        findViewById<TextView>(R.id.recoverpass).setOnClickListener {
            //val intent = Intent(this,RecoverPassActivity::class.java)
            //startActivity(intent)
        }

    }

    override fun onBackPressed() {
        System.exit(0)
    }
}