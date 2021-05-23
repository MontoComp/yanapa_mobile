package com.montcomp.yanata.Charts.PieChart

import android.content.Context
import android.content.SharedPreferences
import com.montcomp.yanata.Base.ComonDomain.LoginResponse
import com.montcomp.yanata.Base.ComonDomain.LoginResponseZentros
import com.montcomp.yanata.Base.ComonDomain.PeopleResponse
import com.montcomp.yanata.Login.LoginActivity


interface PieChartPresenterToViewProtocol {
    val context: Context
    var presenter: PieChartViewToPresenterProtocol?
    fun showPieChart(argument:ArrayList<PeopleResponse>)
}

interface PieChartInteractorToPresenterProtocol {

    fun piechartSucceed(result: ArrayList<PeopleResponse>)
    fun piechartError(error: Throwable)
}

interface PieChartPresentorToInteractorProtocol {
    var presenter: PieChartInteractorToPresenterProtocol?
    fun getPieChart(district:String)
}

interface PieChartViewToPresenterProtocol {
    var view:PieChartPresenterToViewProtocol?
    var interector: PieChartPresentorToInteractorProtocol?
    var router: PieChartPresenterToRouterProtocol?

    fun validatePieChart(district:String)

}

interface PieChartPresenterToRouterProtocol {
    companion object {
        fun configure(activity: PieChartActivity) {}
    }
}