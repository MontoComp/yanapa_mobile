package com.montcomp.yanata.AddComment

import com.montcomp.yanata.Base.ComonDomain.ForumRequest
import com.montcomp.yanata.Base.ComonDomain.ProjectsRequest
import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse
import com.montcomp.yanata.Base.NetworkLayer.apiService
import com.montcomp.yanata.Base.NetworkLayer.disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class BottomSheetFragmentInteractor: BottomSheetFragmentPresentorToInteractorProtocol {
    override var presenter: BottomSheetFragmentInteractorToPresenterProtocol? = null

    override fun getProjectsNames() {

        disposable = apiService.myprojectsnames()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->

                    presenter?.projectsnamesSucceed(result as ArrayList<ProjectsResponse>)

                    /*val x = result as? BaseDomain
                    if(x != null) {
                        x.validate {
                            presenter?.loginSucceed(result)
                        }
                    }*/


                },
                { error ->
                    presenter?.projectsnamesError(error)
                })
    }

    override fun postComment(forumRequest: ForumRequest) {

        disposable = apiService.mypostforum(forumRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->

                    presenter?.postcommentSucceed(result)

                    /*val x = result as? BaseDomain
                    if(x != null) {
                        x.validate {
                            presenter?.loginSucceed(result)
                        }
                    }*/


                },
                { error ->
                    presenter?.postcommentError(error)
                })
    }

}