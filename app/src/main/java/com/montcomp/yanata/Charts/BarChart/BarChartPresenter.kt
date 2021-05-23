package com.montcomp.yanata.Charts.BarChart

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.hcr.hcr_android.Base.PersistentData.Constants
import com.hcr.hcr_android.Base.PersistentData.Keys
import com.montcomp.yanata.Base.ComonDomain.LoginResponse
import com.montcomp.yanata.Base.ComonDomain.LoginResponseZentros
import com.montcomp.yanata.Base.ComonDomain.MesageErrorResponse
import com.montcomp.yanata.Base.ComonDomain.PeopleResponse
import com.montcomp.yanata.Base.NetworkLayer.DisposableActivity
import com.montcomp.yanata.R
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory


class BarChartPresenter : BarChartViewToPresenterProtocol, BarChartInteractorToPresenterProtocol {

    override var view: BarChartPresenterToViewProtocol? = null
    override var interector: BarChartPresentorToInteractorProtocol? = null
    override var router: BarChartPresenterToRouterProtocol? = null

    override fun validateBarChart(district:String) {
        val loader = view as DisposableActivity

        loader.displayWaiting()

        interector?.getBarChart(district)

    }

    override fun barchartSucceed(result: ArrayList<PeopleResponse>) {
        val loader = view as DisposableActivity
        loader.hideWaiting()
        view?.showBarChart(result)
    }

    override fun barchartError(error: Throwable) {
        val loader = view as DisposableActivity
        loader.hideWaiting()
        Toast.makeText(view?.context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }



}
