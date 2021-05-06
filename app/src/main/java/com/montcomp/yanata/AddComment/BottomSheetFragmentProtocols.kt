package com.montcomp.yanata.AddComment

import android.content.Context
import com.montcomp.yanata.Base.ComonDomain.ForumRequest
import com.montcomp.yanata.Base.ComonDomain.ProjectsRequest
import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse


interface BottomSheetFragmentPresenterToViewProtocol {
    val contxt: Context?
    var presenter: BottomSheetFragmentViewToPresenterProtocol?
    fun displayWaiting()
    fun hideWaiting()

    fun showProjectsNames(argument:ArrayList<ProjectsResponse>)
    fun showPostComment(argument:Any)
}

interface BottomSheetFragmentInteractorToPresenterProtocol {

    fun projectsnamesSucceed(result: ArrayList<ProjectsResponse>)
    fun projectsnamesError(error: Throwable)
    fun postcommentSucceed(result: Any)
    fun postcommentError(error: Throwable)

}

interface BottomSheetFragmentPresentorToInteractorProtocol {
    var presenter: BottomSheetFragmentInteractorToPresenterProtocol?

    fun getProjectsNames()
    fun postComment(forumRequest:ForumRequest)
}

interface BottomSheetFragmentViewToPresenterProtocol {

    var view:BottomSheetFragmentPresenterToViewProtocol?
    var interector: BottomSheetFragmentPresentorToInteractorProtocol?
    var router: BottomSheetFragmentPresenterToRouterProtocol?

    fun validateProjectsNames()
    fun validatePostComment(forumRequest: ForumRequest)

}

interface BottomSheetFragmentPresenterToRouterProtocol {
    companion object {
        fun configure(activity: BottomSheetFragment) {}
    }
}