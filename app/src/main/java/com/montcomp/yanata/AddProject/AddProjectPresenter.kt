package com.montcomp.yanata.AddProject

import android.view.WindowManager
import android.widget.Toast
import com.montcomp.yanata.Base.ComonDomain.ProjectsRequest

import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse
import com.montcomp.yanata.Base.NetworkLayer.DisposableActivity
import kotlin.collections.ArrayList


class AddProjectPresenter : AddProjectViewToPresenterProtocol, AddProjectInteractorToPresenterProtocol {

    override var view: AddProjectPresenterToViewProtocol? = null
    override var interector: AddProjectPresentorToInteractorProtocol? = null
    override var router: AddProjectPresenterToRouterProtocol? = null


    override fun validatePostProject(projectsRequest: ProjectsRequest) {

        val loader = view as DisposableActivity

        loader.displayWaiting()
        loader.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

        interector?.postProject(projectsRequest)

    }

    override fun postprojectSucceed(result: Any) {
        val loader = view as DisposableActivity
        loader.hideWaiting()
        loader.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        view?.showPostProject(result)
    }

    override fun postprojectError(error: Throwable) {
        val loader = view as DisposableActivity
        loader.hideWaiting()
        loader.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        Toast.makeText(view?.context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

}
