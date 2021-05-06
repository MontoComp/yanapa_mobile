package com.montcomp.yanata.Forum

import com.montcomp.yanata.Base.ComonDomain.ForumResponse
import com.montcomp.yanata.Base.ComonDomain.ProjectsRequest
import com.montcomp.yanata.Base.ComonDomain.ProjectsResponse
import com.montcomp.yanata.Base.NetworkLayer.apiService
import com.montcomp.yanata.Base.NetworkLayer.disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ForumFragmentInteractor: ForumFragmentPresentorToInteractorProtocol {
    override var presenter: ForumFragmentInteractorToPresenterProtocol? = null

    override fun getForum() {

        disposable = apiService.myforum()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->

                    presenter?.forumSucceed(result as ArrayList<ForumResponse>)

                    /*val x = result as? BaseDomain
                    if(x != null) {
                        x.validate {
                            presenter?.loginSucceed(result)
                        }
                    }*/


                },
                { error ->
                    presenter?.forumError(error)
                })
    }


}