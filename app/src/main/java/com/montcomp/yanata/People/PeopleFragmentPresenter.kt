package com.montcomp.yanata.People

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Environment
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.hcr.hcr_android.Base.PersistentData.Constants
import com.hcr.hcr_android.Base.PersistentData.Keys
import com.montcomp.yanata.Base.ComonDomain.PeopleResponse
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.Base64.getDecoder
import kotlin.collections.ArrayList


class PeopleFragmentPresenter : PeopleFragmentViewToPresenterProtocol, PeopleFragmentInteractorToPresenterProtocol {

    override var view: PeopleFragmentPresenterToViewProtocol? = null
    override var interector: PeopleFragmentPresentorToInteractorProtocol? = null
    override var router: PeopleFragmentPresenterToRouterProtocol? = null


    override fun validatePeople() {
        /*val loader = view as DisposableActivity

        loader.displayWaiting()
        loader.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)*/
        view?.displayWaiting()

        interector?.getPeople()

    }

    override fun peopleSucceed(result: ArrayList<PeopleResponse>) {
        view?.hideWaiting()

        view?.showPeople(result)
    }

    override fun peopleError(error: Throwable) {
        view?.hideWaiting()
        /*val loader = view as DisposableActivity
        loader.hideWaiting()
        loader.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)*/
        Toast.makeText(view?.contxt, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
        /*if(error is HttpException){

            val exception: HttpException = error as HttpException
            val response: Response<*> = exception.response()
            var converter: Converter<ResponseBody?, MesageErrorResponse>? = GsonConverterFactory.create().responseBodyConverter(
                MesageErrorResponse::class.java, arrayOfNulls<Annotation>(0),null) as Converter<ResponseBody?, MesageErrorResponse>?

            val newerror: MesageErrorResponse = converter!!.convert(response.errorBody())

            when(error.code()){
                400->{

                    var alert= AlertDialog.Builder(view!!.contxt!!)
                    alert.setIcon(view!!.contxt!!.resources.getDrawable(R.drawable.ic_alert_2))
                    alert.setTitle("Mensaje de error")

                    alert.setMessage(newerror.message)
                    alert.setOnDismissListener {dialog ->

                        //

                    }
                    alert.show()
                }
                404->{

                    var alert= AlertDialog.Builder(view!!.contxt!!)
                    alert.setIcon(view!!.contxt!!.resources.getDrawable(R.drawable.ic_alert_2))
                    alert.setTitle("Mensaje de error")

                    alert.setMessage(newerror.message)
                    alert.setOnDismissListener {dialog ->

                        //

                    }
                    alert.show()
                }
                500->{

                    var alert= AlertDialog.Builder(view!!.contxt!!)
                    alert.setIcon(view!!.contxt!!.resources.getDrawable(R.drawable.ic_alert_2))
                    alert.setTitle("Mensaje de error")

                    alert.setMessage(newerror.message)
                    alert.setOnDismissListener {dialog ->

                        //

                    }
                    alert.show()
                }// manage 404 error
                else->{
                    Toast.makeText(view?.contxt, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
                }// manage anything else
            }
            //Toast.makeText(view?.context, error.code().toString(), Toast.LENGTH_SHORT).show()

        }else{
            //Toast.makeText(view?.context, "Revise su conexión a internet", Toast.LENGTH_SHORT).show()
        }*/
    }


}
