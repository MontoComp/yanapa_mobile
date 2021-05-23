package com.montcomp.yanata.Charts.RadarChart

import android.widget.Toast
import com.montcomp.yanata.Base.ComonDomain.PeopleResponse
import com.montcomp.yanata.Base.NetworkLayer.DisposableActivity


class RadarChartPresenter : RadarChartViewToPresenterProtocol, RadarChartInteractorToPresenterProtocol {

    override var view: RadarChartPresenterToViewProtocol? = null
    override var interector: RadarChartPresentorToInteractorProtocol? = null
    override var router: RadarChartPresenterToRouterProtocol? = null

    override fun validateRadarChart(district:String) {
        val loader = view as DisposableActivity

        loader.displayWaiting()

        interector?.getRadarChart(district)

    }

    override fun radarchartSucceed(result: ArrayList<PeopleResponse>) {
        val loader = view as DisposableActivity
        loader.hideWaiting()
        view?.showRadarChart(result)
    }

    override fun radarchartError(error: Throwable) {
        val loader = view as DisposableActivity
        loader.hideWaiting()
        Toast.makeText(view?.context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }



}
