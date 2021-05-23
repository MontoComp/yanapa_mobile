package com.montcomp.yanata.Charts.BarChart

import android.content.SharedPreferences
import android.util.Log
import com.hcr.hcr_android.Base.PersistentData.PersistentData
import com.montcomp.yanata.Base.ComonDomain.LoginRequestZentros
import com.montcomp.yanata.Base.ComonDomain.PeopleResponse
import com.montcomp.yanata.Base.NetworkLayer.apiService
import com.montcomp.yanata.Base.NetworkLayer.disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class BarChartInteractor: BarChartPresentorToInteractorProtocol {
    override var presenter: BarChartInteractorToPresenterProtocol? = null

    override fun getBarChart(district:String) {
        disposable = apiService.mydistrict(district)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->

                            presenter?.barchartSucceed(result as ArrayList<PeopleResponse>)

                            /*val x = result as? BaseDomain
                            if(x != null) {
                                x.validate {
                                    presenter?.loginSucceed(result)
                                }
                            }*/


                        },
                        { error ->
                            presenter?.barchartError(error)
                        })
    }

}