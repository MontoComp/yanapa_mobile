package com.montcomp.yanata.Charts.RadarChart

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.widget.Toast
import com.hcr.hcr_android.Base.PersistentData.Keys
import com.hcr.hcr_android.Base.PersistentData.PersistentData
import com.montcomp.yanata.Base.ComonDomain.LoginResponse


class RadarChartRouter: RadarChartPresenterToRouterProtocol {


    companion object {
    fun configure(activity: RadarChartActivity) {
        val view = activity
        val presenter = RadarChartPresenter()
        val interactor = RadarChartInteractor()
        val router = RadarChartRouter()

        view.presenter = presenter
        presenter.view = view
        presenter.router = router
        presenter.interector = interactor
        interactor.presenter = presenter

        }

    }

}