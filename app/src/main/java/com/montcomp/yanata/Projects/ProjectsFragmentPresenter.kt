package com.montcomp.yanata.Projects

import android.widget.Toast
import com.montcomp.yanata.Base.ComonDomain.ProjectsRequest

import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse
import kotlin.collections.ArrayList


class ProjectsFragmentPresenter : ProjectsFragmentViewToPresenterProtocol, ProjectsFragmentInteractorToPresenterProtocol {

    override var view: ProjectsFragmentPresenterToViewProtocol? = null
    override var interector: ProjectsFragmentPresentorToInteractorProtocol? = null
    override var router: ProjectsFragmentPresenterToRouterProtocol? = null


    override fun validateProjects() {
        /*val loader = view as DisposableActivity

        loader.displayWaiting()
        loader.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)*/
        view?.displayWaiting()

        interector?.getProjects()

    }

    override fun projectsSucceed(result: ArrayList<ProjectsResponse>) {
        view?.hideWaiting()

        view?.showProjects(result)
    }

    override fun projectsError(error: Throwable) {
        view?.hideWaiting()
        /*val loader = view as DisposableActivity
        loader.hideWaiting()
        loader.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)*/
        Toast.makeText(view?.contxt, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    override fun validatePostProject(projectsRequest: ProjectsRequest) {

        view?.displayWaiting()

        interector?.postProject(projectsRequest)

    }

    override fun postprojectSucceed(result: Any) {
        view?.hideWaiting()
        view?.showPostProject(result)
    }

    override fun postprojectError(error: Throwable) {
        view?.hideWaiting()

        Toast.makeText(view?.contxt, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

}
