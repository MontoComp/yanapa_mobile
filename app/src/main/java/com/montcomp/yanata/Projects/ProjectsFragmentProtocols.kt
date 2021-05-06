package com.montcomp.yanata.Projects

import android.content.Context
import com.montcomp.yanata.Base.ComonDomain.ProjectsRequest
import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse


interface ProjectsFragmentPresenterToViewProtocol {
    val contxt: Context?
    var presenter: ProjectsFragmentViewToPresenterProtocol?
    fun displayWaiting()
    fun hideWaiting()

    fun showProjects(argument:ArrayList<ProjectsResponse>)
    fun showPostProject(argument:Any)
}

interface ProjectsFragmentInteractorToPresenterProtocol {

    fun projectsSucceed(result: ArrayList<ProjectsResponse>)
    fun projectsError(error: Throwable)
    fun postprojectSucceed(result: Any)
    fun postprojectError(error: Throwable)

}

interface ProjectsFragmentPresentorToInteractorProtocol {
    var presenter: ProjectsFragmentInteractorToPresenterProtocol?

    fun getProjects()
    fun postProject(projectsRequest:ProjectsRequest)
}

interface ProjectsFragmentViewToPresenterProtocol {

    var view:ProjectsFragmentPresenterToViewProtocol?
    var interector: ProjectsFragmentPresentorToInteractorProtocol?
    var router: ProjectsFragmentPresenterToRouterProtocol?

    fun validateProjects()
    fun validatePostProject(projectsRequest: ProjectsRequest)

}

interface ProjectsFragmentPresenterToRouterProtocol {
    companion object {
        fun configure(activity: ProjectsFragment) {}
    }
}