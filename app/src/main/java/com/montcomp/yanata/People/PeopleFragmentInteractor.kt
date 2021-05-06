package com.montcomp.yanata.People

import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.hcr.hcr_android.Base.PersistentData.PersistentData
import com.montcomp.yanata.Base.ComonDomain.PeopleResponse
import com.montcomp.yanata.Base.NetworkLayer.apiService
import com.montcomp.yanata.Base.NetworkLayer.disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class PeopleFragmentInteractor: PeopleFragmentPresentorToInteractorProtocol {
    override var presenter: PeopleFragmentInteractorToPresenterProtocol? = null

    override fun getPeople() {

        disposable = apiService.mypeople()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->

                    presenter?.peopleSucceed(result as ArrayList<PeopleResponse>)

                    /*val x = result as? BaseDomain
                    if(x != null) {
                        x.validate {
                            presenter?.loginSucceed(result)
                        }
                    }*/


                },
                { error ->
                    presenter?.peopleError(error)
                })
    }


}