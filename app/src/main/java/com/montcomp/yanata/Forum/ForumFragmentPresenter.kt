package com.montcomp.yanata.Forum

import android.widget.Toast
import com.montcomp.yanata.Base.ComonDomain.ForumResponse
import com.montcomp.yanata.Base.ComonDomain.ProjectsRequest

import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse
import kotlin.collections.ArrayList


class ForumFragmentPresenter : ForumFragmentViewToPresenterProtocol, ForumFragmentInteractorToPresenterProtocol {

    override var view: ForumFragmentPresenterToViewProtocol? = null
    override var interector: ForumFragmentPresentorToInteractorProtocol? = null
    override var router: ForumFragmentPresenterToRouterProtocol? = null


    override fun validateForum() {
        /*val loader = view as DisposableActivity

        loader.displayWaiting()
        loader.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)*/
        view?.displayWaiting()

        interector?.getForum()

    }

    override fun forumSucceed(result: ArrayList<ForumResponse>) {
        view?.hideWaiting()

        view?.showForum(result)
    }

    override fun forumError(error: Throwable) {
        view?.hideWaiting()
        /*val loader = view as DisposableActivity
        loader.hideWaiting()
        loader.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)*/
        Toast.makeText(view?.contxt, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }



}
