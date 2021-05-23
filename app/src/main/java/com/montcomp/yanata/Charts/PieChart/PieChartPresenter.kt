package com.montcomp.yanata.Charts.PieChart

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


class PieChartPresenter : PieChartViewToPresenterProtocol, PieChartInteractorToPresenterProtocol {

    override var view: PieChartPresenterToViewProtocol? = null
    override var interector: PieChartPresentorToInteractorProtocol? = null
    override var router: PieChartPresenterToRouterProtocol? = null

    override fun validatePieChart(district:String) {
        val loader = view as DisposableActivity

        loader.displayWaiting()

        interector?.getPieChart(district)

    }

    override fun piechartSucceed(result: ArrayList<PeopleResponse>) {
        val loader = view as DisposableActivity
        loader.hideWaiting()
        view?.showPieChart(result)
    }

    override fun piechartError(error: Throwable) {
        val loader = view as DisposableActivity
        loader.hideWaiting()
        Toast.makeText(view?.context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }



}
