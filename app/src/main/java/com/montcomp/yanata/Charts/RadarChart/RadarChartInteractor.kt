package com.montcomp.yanata.Charts.RadarChart

import android.content.SharedPreferences
import android.util.Log
import com.hcr.hcr_android.Base.PersistentData.PersistentData
import com.montcomp.yanata.Base.ComonDomain.LoginRequestZentros
import com.montcomp.yanata.Base.ComonDomain.PeopleResponse
import com.montcomp.yanata.Base.NetworkLayer.apiService
import com.montcomp.yanata.Base.NetworkLayer.disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RadarChartInteractor: RadarChartPresentorToInteractorProtocol {
    override var presenter: RadarChartInteractorToPresenterProtocol? = null

    override fun getRadarChart(district:String) {
        disposable = apiService.mydistrict(district)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->

                            presenter?.radarchartSucceed(result as ArrayList<PeopleResponse>)

                            /*val x = result as? BaseDomain
                            if(x != null) {
                                x.validate {
                                    presenter?.loginSucceed(result)
                                }
                            }*/


                        },
                        { error ->
                            presenter?.radarchartError(error)
                        })
    }

}