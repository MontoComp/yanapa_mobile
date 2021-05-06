package com.montcomp.yanata.AddProject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.lifecycle.Transformations.map
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.montcomp.yanata.Base.ComonDomain.ProjectsRequest
import com.montcomp.yanata.Base.NetworkLayer.DisposableActivity
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

class AddProjectActivity : DisposableActivity() , AddProjectPresenterToViewProtocol{

    override val context: Context = this
    override var presenter: AddProjectViewToPresenterProtocol? = null


    override fun showPostProject(argument: Any) {
        Toast.makeText(this,"OK",Toast.LENGTH_SHORT).show()
        onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addproject)
        AddProjectRouter.configure(this)

        var title = findViewById<TextInputEditText>(R.id.et_project) as EditText
        var manager = findViewById<TextInputEditText>(R.id.et_manager) as EditText
        var dni = findViewById<TextInputEditText>(R.id.et_dni) as EditText
        var address = findViewById<TextInputEditText>(R.id.et_address) as EditText
        var place = findViewById<TextInputEditText>(R.id.et_place) as EditText
        var phone = findViewById<TextInputEditText>(R.id.et_phone) as EditText

        val toolbarTitle = this.findViewById(R.id.toolbar_title_back)  as TextView?
        toolbarTitle?.setText("Nuevo proyecto")
        toolbarTitle?.setTextColor(this.resources.getColor(R.color.white))

        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            onBackPressed()
        }

        findViewById<Button>(R.id.btn_save_project).setOnClickListener {

            if(!title.text.toString().equals("")){
                if(!manager.text.toString().equals("")){
                    if(!address.text.toString().equals("")){
                        if(!phone.text.toString().equals("")){
                            var mypostproject = ProjectsRequest(title.text.toString(),manager.text.toString(),dni.text.toString().toInt(),address.text.toString(),place.text.toString(),phone.text.toString().toInt())

                            presenter?.validatePostProject(mypostproject)
                        }else{
                            Toast.makeText(this@AddProjectActivity,"Es necesario escribir un número de teléfono",Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this@AddProjectActivity,"Es necesario escribir la dirección",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this@AddProjectActivity,"Es necesario escribir el nombre del encargado ",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this@AddProjectActivity,"Es necesario escribir el nombre del proyecto",Toast.LENGTH_SHORT).show()
            }

        }



    }


}