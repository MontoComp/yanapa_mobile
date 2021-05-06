package com.montcomp.yanata.Login

import android.content.SharedPreferences
import android.util.Log
import com.hcr.hcr_android.Base.PersistentData.PersistentData
import com.montcomp.yanata.Base.ComonDomain.LoginRequestZentros
import com.montcomp.yanata.Base.NetworkLayer.apiService
import com.montcomp.yanata.Base.NetworkLayer.disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LoginInteractor: LoginPresentorToInteractorProtocol {
    override var presenter: LoginInteractorToPresenterProtocol? = null

    override fun getLogin(username:String,password:String) {
        val request = LoginRequestZentros(username, password)
        disposable = apiService.loginEntity(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->

                            PersistentData.instance.userID.value = result.user.codigoUsuario
                            PersistentData.instance.userToken.value = result.token
                            PersistentData.instance.myname.value=result.user.nombrePersona
                            PersistentData.instance.codid.value=result.user.codRolUsuario
                            presenter?.loginSucceed(result)

                            /*val x = result as? BaseDomain
                            if(x != null) {
                                x.validate {
                                    presenter?.loginSucceed(result)
                                }
                            }*/


                        },
                        { error ->
                            presenter?.loginError(error)
                        })
    }

}