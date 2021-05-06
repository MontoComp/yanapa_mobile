package com.montcomp.yanata.Login

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
import com.montcomp.yanata.Base.NetworkLayer.DisposableActivity
import com.montcomp.yanata.R
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory


class LoginPresenter : LoginViewToPresenterProtocol, LoginInteractorToPresenterProtocol {

    var count =0

    override var view: LoginPresenterToViewProtocol? = null
    override var interector: LoginPresentorToInteractorProtocol? = null
    override var router: LoginPresenterToRouterProtocol? = null

    override fun listItemClicked(model: LoginResponse) {
        val view = view ?: return
        router?.navigateToDetail(view.context, model)
    }

    override fun validateLogin(username:String,password:String) {
        val loader = view as DisposableActivity

        loader.displayWaiting()

        interector?.getLogin(username,password)

    }

    override fun loginSucceed(result: LoginResponseZentros) {
        val loader = view as DisposableActivity
        loader.hideWaiting()
        view?.showLogin(result)
    }

    override fun loginError(error: Throwable) {
        count++
        Log.d("COUNTTT","${count}")
        val loader = view as DisposableActivity
        loader.hideWaiting()
        if(error is HttpException){
            /*var alert= AlertDialog.Builder(view!!.context)
            alert.setIcon(view!!.context.resources.getDrawable(R.drawable.ic_alert_2))
            alert.setTitle("Mensaje de error")

            alert.setMessage("Contraseña o usuario incorrectos")
            alert.setOnDismissListener {dialog ->

                //

            }
            alert.show()*/
            when(error.code()){
                401->{
                    val exception: HttpException = error as HttpException
                    val response: Response<*> = exception.response()
                    var converter: Converter<ResponseBody?, MesageErrorResponse>? = GsonConverterFactory.create().responseBodyConverter(
                        MesageErrorResponse::class.java, arrayOfNulls<Annotation>(0),null) as Converter<ResponseBody?, MesageErrorResponse>?

                    val newerror: MesageErrorResponse = converter!!.convert(response.errorBody())

                    if (newerror.reason.equals("ATTEMPTS_EXCEEDED")){
                        var alert= AlertDialog.Builder(view!!.context)
                        alert.setIcon(view!!.context.resources.getDrawable(R.drawable.ic_alert_2))
                        alert.setTitle("Mensaje de error")

                        alert.setMessage(newerror.message)
                        alert.setOnDismissListener {dialog ->

                            /*val intent = Intent(view?.context,RecoverPassActivity::class.java)
                            intent.putExtra("isvalid","ATTEMPTS_EXCEEDED")
                            view?.context?.startActivity(intent)*/

                        }
                        alert.show()
                        //Toast.makeText(view?.context, "Redirigiendo", Toast.LENGTH_SHORT).show()// manage anything else

                    }else{
                        var alert= AlertDialog.Builder(view!!.context)
                        alert.setIcon(view!!.context.resources.getDrawable(R.drawable.ic_alert_2))
                        alert.setTitle("Mensaje de error")

                        alert.setMessage(newerror.message)
                        alert.setOnDismissListener {dialog ->

                            //

                        }
                        alert.show()
                    }

                    //Toast.makeText(view?.context, "${newerror.message}", Toast.LENGTH_SHORT).show()// manage anything else
                } // manage 404 error
                else->{
                    Toast.makeText(view?.context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()// manage anything else
                }// manage anything else
            }
            //Toast.makeText(view?.context, error.code().toString(), Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(view?.context, "Revise su conexión a internet", Toast.LENGTH_SHORT).show()
        }

    }



}
