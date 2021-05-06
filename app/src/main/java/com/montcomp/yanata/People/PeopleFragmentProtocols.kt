package com.montcomp.yanata.People

import android.content.Context
import android.content.SharedPreferences
import android.widget.RelativeLayout
import com.montcomp.yanata.Base.ComonDomain.PeopleResponse


interface PeopleFragmentPresenterToViewProtocol {
    val contxt: Context?
    var presenter: PeopleFragmentViewToPresenterProtocol?
    fun displayWaiting()
    fun hideWaiting()

    fun showPeople(argument:ArrayList<PeopleResponse>)
}

interface PeopleFragmentInteractorToPresenterProtocol {

    fun peopleSucceed(result: ArrayList<PeopleResponse>)
    fun peopleError(error: Throwable)

}

interface PeopleFragmentPresentorToInteractorProtocol {
    var presenter: PeopleFragmentInteractorToPresenterProtocol?

    fun getPeople()
}

interface PeopleFragmentViewToPresenterProtocol {

    var view:PeopleFragmentPresenterToViewProtocol?
    var interector: PeopleFragmentPresentorToInteractorProtocol?
    var router: PeopleFragmentPresenterToRouterProtocol?

    fun validatePeople()

}

interface PeopleFragmentPresenterToRouterProtocol {
    companion object {
        fun configure(activity: PeopleFragment) {}
    }
}