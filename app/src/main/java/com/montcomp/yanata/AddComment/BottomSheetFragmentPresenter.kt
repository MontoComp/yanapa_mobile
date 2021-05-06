package com.montcomp.yanata.AddComment

import android.widget.Toast
import com.montcomp.yanata.Base.ComonDomain.ForumRequest
import com.montcomp.yanata.Base.ComonDomain.ProjectsRequest

import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse
import kotlin.collections.ArrayList


class BottomSheetFragmentPresenter : BottomSheetFragmentViewToPresenterProtocol, BottomSheetFragmentInteractorToPresenterProtocol {

    override var view: BottomSheetFragmentPresenterToViewProtocol? = null
    override var interector: BottomSheetFragmentPresentorToInteractorProtocol? = null
    override var router: BottomSheetFragmentPresenterToRouterProtocol? = null


    override fun validateProjectsNames() {
        /*val loader = view as DisposableActivity

        loader.displayWaiting()
        loader.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)*/
        view?.displayWaiting()

        interector?.getProjectsNames()

    }

    override fun projectsnamesSucceed(result: ArrayList<ProjectsResponse>) {
        view?.hideWaiting()

        view?.showProjectsNames(result)
    }

    override fun projectsnamesError(error: Throwable) {
        view?.hideWaiting()
        /*val loader = view as DisposableActivity
        loader.hideWaiting()
        loader.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)*/
        Toast.makeText(view?.contxt, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    override fun validatePostComment(forumRequest: ForumRequest) {

        view?.displayWaiting()

        interector?.postComment(forumRequest)

    }

    override fun postcommentSucceed(result: Any) {
        view?.hideWaiting()
        view?.showPostComment(result)
    }

    override fun postcommentError(error: Throwable) {
        view?.hideWaiting()

        Toast.makeText(view?.contxt, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

}
