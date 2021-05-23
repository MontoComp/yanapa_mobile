package com.montcomp.yanata.Charts.RadarChart

import android.content.Context
import android.content.SharedPreferences
import com.montcomp.yanata.Base.ComonDomain.LoginResponse
import com.montcomp.yanata.Base.ComonDomain.LoginResponseZentros
import com.montcomp.yanata.Base.ComonDomain.PeopleResponse
import com.montcomp.yanata.Login.LoginActivity


interface RadarChartPresenterToViewProtocol {
    val context: Context
    var presenter: RadarChartViewToPresenterProtocol?
    fun showRadarChart(argument:ArrayList<PeopleResponse>)
}

interface RadarChartInteractorToPresenterProtocol {

    fun radarchartSucceed(result: ArrayList<PeopleResponse>)
    fun radarchartError(error: Throwable)
}

interface RadarChartPresentorToInteractorProtocol {
    var presenter: RadarChartInteractorToPresenterProtocol?
    fun getRadarChart(district:String)
}

interface RadarChartViewToPresenterProtocol {
    var view:RadarChartPresenterToViewProtocol?
    var interector: RadarChartPresentorToInteractorProtocol?
    var router: RadarChartPresenterToRouterProtocol?

    fun validateRadarChart(district:String)

}

interface RadarChartPresenterToRouterProtocol {
    companion object {
        fun configure(activity: RadarChartActivity) {}
    }
}