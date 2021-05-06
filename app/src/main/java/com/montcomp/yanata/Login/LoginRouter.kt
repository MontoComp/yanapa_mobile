package com.montcomp.yanata.Login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.widget.Toast
import com.hcr.hcr_android.Base.PersistentData.Keys
import com.hcr.hcr_android.Base.PersistentData.PersistentData
import com.montcomp.yanata.Base.ComonDomain.LoginResponse


class LoginRouter: LoginPresenterToRouterProtocol {


    companion object {
    fun configure(activity: LoginActivity) {
        val view = activity
        val presenter = LoginPresenter()
        val interactor = LoginInteractor()
        val router = LoginRouter()

        view.presenter = presenter
        presenter.view = view
        presenter.router = router
        presenter.interector = interactor
        interactor.presenter = presenter

        }




    }


    override fun navigateToDetail(context:Context, model: LoginResponse) {

        var typevideo = PersistentData.instance.video.value

        if (typevideo=="backoffice.videoCallSystem.googleMeet"){
            //var uri = Uri.parse("https://meet.google.com/hys-ogia-zuf")
            Toast.makeText(context,"Hii",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"Hii",Toast.LENGTH_SHORT).show()
        }

    }

}