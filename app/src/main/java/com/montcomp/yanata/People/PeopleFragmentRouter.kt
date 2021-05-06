package com.montcomp.yanata.People

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.widget.Toast
import com.hcr.hcr_android.Base.PersistentData.Keys
import com.hcr.hcr_android.Base.PersistentData.PersistentData


class PeopleFragmentRouter: PeopleFragmentPresenterToRouterProtocol {


    companion object {
    fun configure(activity: PeopleFragment) {
        val view = activity
        val presenter = PeopleFragmentPresenter()
        val interactor = PeopleFragmentInteractor()
        val router = PeopleFragmentRouter()

        view.presenter = presenter
        presenter.view = view
        presenter.router = router
        presenter.interector = interactor
        interactor.presenter = presenter

        }


    }

}