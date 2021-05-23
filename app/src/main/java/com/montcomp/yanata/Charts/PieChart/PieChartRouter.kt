package com.montcomp.yanata.Charts.PieChart

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.widget.Toast
import com.hcr.hcr_android.Base.PersistentData.Keys
import com.hcr.hcr_android.Base.PersistentData.PersistentData
import com.montcomp.yanata.Base.ComonDomain.LoginResponse


class PieChartRouter: PieChartPresenterToRouterProtocol {


    companion object {
    fun configure(activity: PieChartActivity) {
        val view = activity
        val presenter = PieChartPresenter()
        val interactor = PieChartInteractor()
        val router = PieChartRouter()

        view.presenter = presenter
        presenter.view = view
        presenter.router = router
        presenter.interector = interactor
        interactor.presenter = presenter

        }

    }

}