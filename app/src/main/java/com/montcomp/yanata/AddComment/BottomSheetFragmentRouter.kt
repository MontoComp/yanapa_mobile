package com.montcomp.yanata.AddComment


class BottomSheetFragmentRouter: BottomSheetFragmentPresenterToRouterProtocol {


    companion object {
    fun configure(activity: BottomSheetFragment) {
        val view = activity
        val presenter = BottomSheetFragmentPresenter()
        val interactor = BottomSheetFragmentInteractor()
        val router = BottomSheetFragmentRouter()

        view.presenter = presenter
        presenter.view = view
        presenter.router = router
        presenter.interector = interactor
        interactor.presenter = presenter

        }


    }

}