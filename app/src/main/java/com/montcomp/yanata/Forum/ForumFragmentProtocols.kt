package com.montcomp.yanata.Forum

import android.content.Context
import com.montcomp.yanata.Base.ComonDomain.ForumResponse
import com.montcomp.yanata.Base.ComonDomain.ProjectsRequest
import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse


interface ForumFragmentPresenterToViewProtocol {
    val contxt: Context?
    var presenter: ForumFragmentViewToPresenterProtocol?
    fun displayWaiting()
    fun hideWaiting()

    fun showForum(argument:ArrayList<ForumResponse>)
}

interface ForumFragmentInteractorToPresenterProtocol {

    fun forumSucceed(result: ArrayList<ForumResponse>)
    fun forumError(error: Throwable)

}

interface ForumFragmentPresentorToInteractorProtocol {
    var presenter: ForumFragmentInteractorToPresenterProtocol?

    fun getForum()
}

interface ForumFragmentViewToPresenterProtocol {

    var view:ForumFragmentPresenterToViewProtocol?
    var interector: ForumFragmentPresentorToInteractorProtocol?
    var router: ForumFragmentPresenterToRouterProtocol?

    fun validateForum()

}

interface ForumFragmentPresenterToRouterProtocol {
    companion object {
        fun configure(activity: ForumFragment) {}
    }
}