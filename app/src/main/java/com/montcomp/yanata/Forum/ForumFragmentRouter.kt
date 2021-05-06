package com.montcomp.yanata.Forum



class ForumFragmentRouter: ForumFragmentPresenterToRouterProtocol {


    companion object {
    fun configure(activity: ForumFragment) {
        val view = activity
        val presenter = ForumFragmentPresenter()
        val interactor = ForumFragmentInteractor()
        val router = ForumFragmentRouter()

        view.presenter = presenter
        presenter.view = view
        presenter.router = router
        presenter.interector = interactor
        interactor.presenter = presenter

        }


    }

}