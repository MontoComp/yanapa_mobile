package com.montcomp.yanata.Charts.BarChart

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.widget.Toast
import com.hcr.hcr_android.Base.PersistentData.Keys
import com.hcr.hcr_android.Base.PersistentData.PersistentData
import com.montcomp.yanata.Base.ComonDomain.LoginResponse


class BarChartRouter: BarChartPresenterToRouterProtocol {


    companion object {
    fun configure(activity: BarChartActivity) {
        val view = activity
        val presenter = BarChartPresenter()
        val interactor = BarChartInteractor()
        val router = BarChartRouter()

        view.presenter = presenter
        presenter.view = view
        presenter.router = router
        presenter.interector = interactor
        interactor.presenter = presenter

        }

    }

}