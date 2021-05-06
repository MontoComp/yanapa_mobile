package com.montcomp.yanata.AddProject

import android.content.Context
import com.montcomp.yanata.Base.ComonDomain.ProjectsRequest

interface AddProjectPresenterToViewProtocol {
    val context: Context?
    var presenter: AddProjectViewToPresenterProtocol?

    fun showPostProject(argument:Any)
}

interface AddProjectInteractorToPresenterProtocol {

    fun postprojectSucceed(result: Any)
    fun postprojectError(error: Throwable)

}

interface AddProjectPresentorToInteractorProtocol {
    var presenter: AddProjectInteractorToPresenterProtocol?

    fun postProject(projectsRequest:ProjectsRequest)
}

interface AddProjectViewToPresenterProtocol {

    var view:AddProjectPresenterToViewProtocol?
    var interector: AddProjectPresentorToInteractorProtocol?
    var router: AddProjectPresenterToRouterProtocol?

    fun validatePostProject(projectsRequest: ProjectsRequest)

}

interface AddProjectPresenterToRouterProtocol {
    companion object {
        fun configure(activity: AddProjectActivity) {}
    }
}