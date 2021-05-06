package com.montcomp.yanata.Login

import android.content.Context
import android.content.SharedPreferences
import com.montcomp.yanata.Base.ComonDomain.LoginResponse
import com.montcomp.yanata.Base.ComonDomain.LoginResponseZentros
import com.montcomp.yanata.Login.LoginActivity


interface LoginPresenterToViewProtocol {
    val context: Context
    var presenter: LoginViewToPresenterProtocol?
    fun showLogin(argument: LoginResponseZentros)
}

interface LoginInteractorToPresenterProtocol {

    fun loginSucceed(result: LoginResponseZentros)
    fun loginError(error: Throwable)
}

interface LoginPresentorToInteractorProtocol {
    var presenter: LoginInteractorToPresenterProtocol?
    fun getLogin(username:String,password:String)
}

interface LoginViewToPresenterProtocol {
    var view:LoginPresenterToViewProtocol?
    var interector: LoginPresentorToInteractorProtocol?
    var router: LoginPresenterToRouterProtocol?


    fun validateLogin(username:String,password:String)
    fun listItemClicked(model: LoginResponse)


}

interface LoginPresenterToRouterProtocol {
    companion object {
        fun configure(activity: LoginActivity) {}
    }
    fun navigateToDetail(context:Context, model: LoginResponse)
}