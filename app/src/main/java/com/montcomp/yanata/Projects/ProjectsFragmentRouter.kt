package com.montcomp.yanata.Projects



class ProjectsFragmentRouter: ProjectsFragmentPresenterToRouterProtocol {


    companion object {
    fun configure(activity: ProjectsFragment) {
        val view = activity
        val presenter = ProjectsFragmentPresenter()
        val interactor = ProjectsFragmentInteractor()
        val router = ProjectsFragmentRouter()

        view.presenter = presenter
        presenter.view = view
        presenter.router = router
        presenter.interector = interactor
        interactor.presenter = presenter

        }


    }

}