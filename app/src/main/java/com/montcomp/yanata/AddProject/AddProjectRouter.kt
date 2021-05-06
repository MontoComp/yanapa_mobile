package com.montcomp.yanata.AddProject



class AddProjectRouter: AddProjectPresenterToRouterProtocol {


    companion object {
    fun configure(activity: AddProjectActivity) {
        val view = activity
        val presenter = AddProjectPresenter()
        val interactor = AddProjectInteractor()
        val router = AddProjectRouter()

        view.presenter = presenter
        presenter.view = view
        presenter.router = router
        presenter.interector = interactor
        interactor.presenter = presenter

        }


    }

}