package com.montcomp.yanata.Charts.BarChart

import android.content.Context
import android.content.SharedPreferences
import com.montcomp.yanata.Base.ComonDomain.LoginResponse
import com.montcomp.yanata.Base.ComonDomain.LoginResponseZentros
import com.montcomp.yanata.Base.ComonDomain.PeopleResponse
import com.montcomp.yanata.Login.LoginActivity


interface BarChartPresenterToViewProtocol {
    val context: Context
    var presenter: BarChartViewToPresenterProtocol?
    fun showBarChart(argument:ArrayList<PeopleResponse>)
}

interface BarChartInteractorToPresenterProtocol {

    fun barchartSucceed(result: ArrayList<PeopleResponse>)
    fun barchartError(error: Throwable)
}

interface BarChartPresentorToInteractorProtocol {
    var presenter: BarChartInteractorToPresenterProtocol?
    fun getBarChart(district:String)
}

interface BarChartViewToPresenterProtocol {
    var view:BarChartPresenterToViewProtocol?
    var interector: BarChartPresentorToInteractorProtocol?
    var router: BarChartPresenterToRouterProtocol?

    fun validateBarChart(district:String)

}

interface BarChartPresenterToRouterProtocol {
    companion object {
        fun configure(activity: BarChartActivity) {}
    }
}