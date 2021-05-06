package com.montcomp.yanata.AddProject

import com.montcomp.yanata.Base.ComonDomain.ProjectsRequest
import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse
import com.montcomp.yanata.Base.NetworkLayer.apiService
import com.montcomp.yanata.Base.NetworkLayer.disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class AddProjectInteractor: AddProjectPresentorToInteractorProtocol {
    override var presenter: AddProjectInteractorToPresenterProtocol? = null


    override fun postProject(projectsRequest: ProjectsRequest) {

        disposable = apiService.mypostssend(projectsRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->

                    presenter?.postprojectSucceed(result)

                    /*val x = result as? BaseDomain
                    if(x != null) {
                        x.validate {
                            presenter?.loginSucceed(result)
                        }
                    }*/


                },
                { error ->
                    presenter?.postprojectError(error)
                })
    }

}